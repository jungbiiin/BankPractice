package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.dto.GetAccountDto
import org.practice.bank.dto.QGetAccountDto
import org.practice.bank.infrastructure.persistence.entity.QAccountEntity.accountEntity
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) : UserRepository {

    override fun createUser(userName: String, password: String): Int {
        val entity = UserEntity(null, userName, password)
        entityManager.persist(entity)
        return entity.id!!
    }

    fun updateUser(userId: Int, userName: String, password: String) {
        val entity = UserEntity(userId, userName, password)
        entityManager.persist(entity)
    }

    override fun getAccountList(): List<GetAccountDto> {
        return jpaQueryFactory.select(
            QGetAccountDto(
                accountEntity.id,
                accountEntity.balance
            )
        ).from(accountEntity)
            .fetch()
    }

}