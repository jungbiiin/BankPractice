package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.domains.user.repository.UserRepository
import org.practice.bank.infrastructure.persistence.entity.QUserEntity.userEntity
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

    override fun checkExistName(userName: String): Boolean {
        val count = jpaQueryFactory
            .select(userEntity.count())
            .from(userEntity)
            .where(userEntity.userName.eq(userName))
            .fetchCount()

        return count > 0
    }

}