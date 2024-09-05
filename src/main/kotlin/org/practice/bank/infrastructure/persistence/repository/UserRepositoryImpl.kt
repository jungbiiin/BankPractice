package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.dto.GetAccountDto
import org.practice.bank.dto.QGetAccountDto
import org.practice.bank.infrastructure.persistence.entity.QAccountEntity.accountEntity
import org.practice.bank.infrastructure.persistence.entity.QUserEntity
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) : UserRepository {

    override fun createUser(userName: String, password: String): UserEntity {
        val entity = UserEntity(null, userName, password)
        entityManager.persist(entity)
        return entity
    }

    override fun getAccountList(userId:Int): List<GetAccountDto> {
        return jpaQueryFactory.select(
            QGetAccountDto(
                accountEntity.id,
                accountEntity.balance
            )
        ).where(accountEntity.userId.eq(userId)).from(accountEntity)
            .fetch()
    }

    override fun getUserIdList(): List<Int> {
        val user = QUserEntity.userEntity

        return jpaQueryFactory.select(user.id).from(user).fetch()
    }

}