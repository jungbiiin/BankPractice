package org.practice.bank.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.practice.bank.BankApplication
import org.practice.bank.domains.account.dto.CreateAccountDto
import org.practice.bank.domains.account.service.AccountService
import org.practice.bank.domains.user.dto.CreateUserDto
import org.practice.bank.domains.user.repository.UserRepository
import org.practice.bank.domains.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class UserServiceTests {
    @Autowired(required = true)
    private lateinit var userService: UserService

    @Test
    fun createTest1() {
        val res = userService.create(CreateUserDto("qwerqwer", "1234567890"));
        assertEquals("qwerqwer", res.name)
        assertEquals("1234567890", res.password)
    }

    @Test
    fun createTest2() {
        // 왜 에러가 안나지?
        userService.create(CreateUserDto("qwert", "1234"));
        assertThrows<Exception> {
            userService.create(CreateUserDto("qwert", "1234567890"));
        }
    }
}