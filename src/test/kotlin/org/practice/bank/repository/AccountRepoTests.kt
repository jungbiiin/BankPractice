package org.practice.bank.repository

import org.practice.bank.BankApplication
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.common.vo.Money
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class AccountRepoTests() {
    @Autowired(required = true)
    private lateinit var accountRepository: AccountRepository


    @Transactional
    @Test
    fun createAccountRepoTest() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val res1 = accountRepository.save(account1)
        assertEquals(1, res1.userId)
        assertEquals(0, res1.balance)
    }

    @Transactional
    @Test
    fun findByIdAccountRepoTest() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val res1 = accountRepository.save(account1)
        val res2 = accountRepository.findById(res1.id!!)
        assertEquals(res1.id, res2?.id);
    }

    @Transactional
    @Test
    fun findByUserIdAccountRepoTest() {
        val account1 = Account(
            null,
            1,
            Money(0, "KRW")
        )
        val accountLists = accountRepository.findByUserId(1)
        accountLists.forEach{account->  assertEquals(1, account.userId)}
    }

}