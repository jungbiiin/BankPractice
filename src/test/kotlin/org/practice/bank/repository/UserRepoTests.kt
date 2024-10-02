package org.practice.bank.repository

import org.practice.bank.BankApplication
import org.practice.bank.domains.account.repository.AccountRepository
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
    fun checkExistNameTest() {
        val res1 = userRepository.createUser("testUser", "testPassword")
        val isExist1 = userRepository.checkExistName("testUser")
        assertEquals(true, isExist1)
        val isExist2 = userRepository.checkExistName("asdfsdf")
        assertEquals(false, isExist2)
    }
}