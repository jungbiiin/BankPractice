package org.practice.bank.repository

import org.junit.jupiter.api.Test
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.Money
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [AccountHistoryRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class AccountHistoryRepoTests {
    @Autowired(required = true)
    private lateinit var accountRepository: AccountRepository

    @Autowired(required = true)
    private lateinit var accountHistoryRepository: AccountHistoryRepository

    @Transactional
    @Test
    fun getAccountHistory() {
        val account1 = accountRepository.createAccount(1, "KRW")
        val account2 = accountRepository.createAccount(1, "KRW")
        accountRepository.addMoney(account1.id!!, Money(2000, "KRW"), null)
        accountRepository.addMoney(account1.id!!, Money(5000, "KRW"), account2.id)
        val res = accountHistoryRepository.getHistoriesByAccount(account1.id!!)

        assertEquals(account1.id, res[0].accountId)
        assertEquals(2000, res[0].amount)
        assertEquals(null, res[0].transactionAccountId)

        assertEquals(account1.id, res[1].accountId)
        assertEquals(7000, res[1].amount)
        assertEquals(account2.id, res[1].transactionAccountId)
    }
}