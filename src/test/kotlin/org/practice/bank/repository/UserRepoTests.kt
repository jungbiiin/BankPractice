package org.practice.bank.repository

import org.junit.jupiter.api.assertThrows
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.user.model.User
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.annotation.Order
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
    @Order(1)
    fun createUserRepoTest() {
        val res1 = userRepository.save(User(null, "testUser", "testPassword"))
        assertEquals("testUser", res1.name)
        assertEquals("testPassword", res1.password)
    }

    @Transactional
    @Test
    @Order(2)
    fun checkExistNameTest() {
        userRepository.save(User(null, "testUser", "testPassword"))
        assertThrows<Exception> {
            userRepository.save(User(null, "testUser", "testPassword"))
        }
    }
}