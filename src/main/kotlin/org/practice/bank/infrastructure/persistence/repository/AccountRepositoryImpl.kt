package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.LockModeType
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.common.vo.Money
import org.practice.bank.infrastructure.persistence.entity.AccountEntity
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity
import org.practice.bank.infrastructure.persistence.entity.QAccountEntity.accountEntity
import org.practice.bank.domains.account.repository.AccountRepository
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory,
) : AccountRepository {

    override fun save(account: Account): AccountEntity {
        val accountEntity = AccountEntity(
            account.id,
            account.userId,
            account.balance.amount,
            account.balance.currency,
        )
        if (accountEntity.id == null) {
            entityManager.persist(accountEntity)
        } else {
            entityManager.merge(accountEntity)
        }

        return accountEntity;
    }

    override fun findById(accountId: Int): Account? {
        val accountEntity = jpaQueryFactory.selectFrom(accountEntity)
            .where(accountEntity.id.eq(accountId))
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetchOne() ?: return null
        return Account(
            accountEntity.id,
            accountEntity.userId,
            Money(
                accountEntity.balance,
                accountEntity.currency
            )
        )
    }

    override fun findByUserId(userId: Int): List<Account> {
        val accountEntities = jpaQueryFactory.selectFrom(accountEntity)
            .where(accountEntity.userId.eq(userId))
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch()


        return accountEntities.map { accountEntity ->
            Account(
                accountEntity.id,
                accountEntity.userId,
                Money(
                    accountEntity.balance,
                    accountEntity.currency
                )
            )
        }
    }

}