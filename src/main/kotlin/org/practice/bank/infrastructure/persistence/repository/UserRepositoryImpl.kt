package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.hibernate.exception.ConstraintViolationException
import org.practice.bank.domains.user.model.User
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.domains.user.repository.UserRepository
import org.practice.bank.infrastructure.persistence.exception.DuplicateUserNameException
import org.springframework.stereotype.Repository


@Repository
class UserRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) : UserRepository {

    override fun save(user:User): User {
        val entity = UserEntity(null, user.name, user.password)
        try {
            entityManager.persist(entity)
        } catch (e: ConstraintViolationException) {
            if(e.kind == ConstraintViolationException.ConstraintKind.UNIQUE) {
                if(e.constraintName == "user_name") {
                    throw DuplicateUserNameException(user.name)
                }
            }
            throw e
        }
        return User(entity.id, entity.userName, entity.userPassword)
    }
}