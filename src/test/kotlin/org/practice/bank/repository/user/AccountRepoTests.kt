package org.practice.bank.repository.user

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.Money
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

    @Transactional
    @Test
    fun addMoneyTest() {
        val res1 = accountRepository.createAccount(1, "KRW")
        val hisRes = accountRepository.addMoney(res1.id!!, Money(2000,"KRW"))
        assertEquals(res1.id, hisRes.accountId)
        assertEquals(2000, hisRes.amount)
        println(hisRes.createDatetime) // 이거 왜 값이 -999999999-01-01T00:00
    }

    @Transactional
    @Test
    fun addDifferentCurrencyMoneyTest() {
        val res1 = accountRepository.createAccount(1, "KRW")
        assertThrows<Exception> {
            accountRepository.addMoney(res1.id!!, Money(2000,"JPN"))
        }
    }

    @Transactional
    @Test
    fun subtractMoneyTest() {
        val res1 = accountRepository.createAccount(1, "KRW")
        accountRepository.addMoney(res1.id!!, Money(10000, "KRW"))
        val hisRes= accountRepository.subtractMoney(res1.id!!, Money(2000, "KRW"))
        assertEquals(res1.id, hisRes.accountId)
        assertEquals(8000, hisRes.amount)
    }

    @Transactional
    @Test
    fun overSubtractMoneyTest() {
        val res1 = accountRepository.createAccount(1, "KRW")
        accountRepository.addMoney(res1.id!!, Money(10000, "KRW"))
        assertThrows<Exception> {
            accountRepository.subtractMoney(res1.id!!, Money(12000, "KRW"))
        }
    }
}