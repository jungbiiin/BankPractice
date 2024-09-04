package org.practice.bank.repository.user

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.practice.bank.BankApplication
import org.practice.bank.repository.AccountRepository
import org.practice.bank.repository.UserRepository
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
class AccountRepoTests {

    @Autowired(required=true)
    private lateinit var accountRepository: AccountRepository

    @Transactional
    @Test
    fun createAccountTest() {
        val res1 = accountRepository.createAccount(1, "KRW")
        assertEquals(1, res1.userId)
        assertEquals("KRW", res1.currency)
    }

    @Transactional
    @Test
    fun createJpnAccountTest() {
        val res1 = accountRepository.createAccount(1, "JPN")
        assertEquals(1, res1.userId)
        assertEquals("JPN", res1.currency)
    }

}