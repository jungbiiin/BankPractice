package org.practice.bank.repository

import org.junit.jupiter.api.Test
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.common.vo.Money
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class AccountHistoryRepoTests {
    @Autowired(required = true)
    private lateinit var accountHistoryRepository: AccountHistoryRepository

    @Autowired(required = true)
    private lateinit var accountRepository: AccountRepository


    @Transactional
    @Test
    fun getHistoriesByAccount() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val accountRes = accountRepository.save(account1)

        val res1 = accountHistoryRepository.saveHistory(1, accountRes.balance,10000, 2);
        val res2 = accountHistoryRepository.saveHistory(1,  accountRes.balance,20000, 3);
        val historyList = accountHistoryRepository.getHistoriesByAccount(1);
        assertEquals(2, historyList.size)
        assertEquals(res1, historyList[0]);
    }

    @Test
    @Transactional
    fun saveHistory() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val res1 = accountRepository.save(account1)

        val res = accountHistoryRepository.saveHistory(res1.userId, res1.balance,10000, 2);
        assertEquals(10000, res.difference)
    }
}