package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.domains.account.Account
import org.practice.bank.domains.account.Money
import org.practice.bank.dto.GetAccountDto
import org.practice.bank.dto.QGetAccountDto
import org.practice.bank.infrastructure.persistence.entity.AccountEntity
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity
import org.practice.bank.infrastructure.persistence.entity.QAccountEntity.accountEntity
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.practice.bank.repository.AccountRepository
import org.practice.bank.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AccountRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory,
) : AccountRepository {
    override fun createAccount(userId: Int, currency: String): AccountEntity {
        val entity = AccountEntity(null, userId, 0, currency)
        entityManager.persist(entity)
        return entity
    }

    override fun addMoney(accountId: Int, money: Money): AccountHistoryEntity {
        val findAccount = entityManager.find(AccountEntity::class.java, accountId)
            ?: throw IllegalArgumentException("Account not found with id: $accountId")

        if (findAccount.currency != money.currency) {
            throw IllegalArgumentException("Currency not match ${money.currency} and ${findAccount.currency}")
        }


        findAccount.balance += money.amount
        val newHistory = AccountHistoryEntity(null, findAccount.id!!, findAccount.balance)
        entityManager.persist(findAccount)
        return newHistory
    }

    override fun subtractMoney(accountId: Int, money: Money): AccountHistoryEntity {
        val findAccount = entityManager.find(AccountEntity::class.java, accountId)
            ?: throw IllegalArgumentException("Account not found with id: $accountId")

        if (findAccount.currency != money.currency) {
            throw IllegalArgumentException("Currency not match ${money.currency} and ${findAccount.currency}")
        }
        if (findAccount.balance - money.amount < 0) {
            throw IllegalArgumentException("balance can not be negative")
        }
        findAccount.balance -= money.amount
        val newHistory = AccountHistoryEntity(null, findAccount.id!!, findAccount.balance)
        entityManager.persist(findAccount)
        return newHistory
    }

    override fun getHistory(userId: Int): List<AccountHistoryEntity> {
        TODO("Not yet implemented")
    }

    override fun deleteAccount(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun getUserAccount(userId: Int): List<AccountEntity> {
        TODO("Not yet implemented")
    }
}