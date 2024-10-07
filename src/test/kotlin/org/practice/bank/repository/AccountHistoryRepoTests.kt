package org.practice.bank.repository

import org.junit.jupiter.api.Test
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.account.model.AccountHistory
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.common.vo.Money
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.annotation.Rollback
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

    @Test
    @Order(1)
    @Rollback(false)
    @Transactional
    fun saveHistory() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val accountRes = accountRepository.save(account1)

        accountHistoryRepository.save(AccountHistory(null, 1, accountRes.balance, 10000, 2, null));
        accountHistoryRepository.save(AccountHistory(null, 1, accountRes.balance, 20000, 3, null));
    }

    @Transactional
    @Test
    @Order(2)
    fun getHistoriesByAccount() {
        val historyList = accountHistoryRepository.getHistoriesByAccount(1);
        assertEquals(2, historyList.size)
    }
}