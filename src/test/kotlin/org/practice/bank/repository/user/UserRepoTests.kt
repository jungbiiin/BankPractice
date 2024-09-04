package org.practice.bank.repository.user

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.practice.bank.BankApplication
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.infrastructure.persistence.repository.UserRepositoryImpl
import org.practice.bank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class UserRepoTests() {

    @Autowired(required=true)
    private lateinit var userRepository: UserRepository


    @Transactional
    @Test
    fun createUserRepoTest() {
        val res1 = userRepository.createUser("testUser", "testPassword")
        assertEquals("testUser",res1.userName)
        assertEquals("testPassword",res1.userPassword)
    }
}