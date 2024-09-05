package org.practice.bank.repository

import org.practice.bank.BankApplication
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
class UserRepoTests() {

    @Autowired(required = true)
    private lateinit var userRepository: UserRepository

    @Autowired(required = true)
    private lateinit var accountRepository: AccountRepository


    @Transactional
    @Test
    fun createUserRepoTest() {
        val res1 = userRepository.createUser("testUser", "testPassword")
        assertEquals("testUser", res1.userName)
        assertEquals("testPassword", res1.userPassword)
    }

    @Transactional
    @Test
    fun getAccountListTest() {
        val res1 = userRepository.createUser("testUser", "testPassword")
        accountRepository.createAccount(res1.id!!, "KRW");
        accountRepository.createAccount(res1.id!!, "KRW");
        val accountList = userRepository.getAccountList(res1.id!!);
        assertEquals(2, accountList.size)
    }
}