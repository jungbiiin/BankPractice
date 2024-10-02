package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity
import org.practice.bank.infrastructure.persistence.entity.QAccountHistoryEntity
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AccountHistoryRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory,
) : AccountHistoryRepository {
    override fun getHistoriesByAccount(accountId: Int): List<AccountHistoryEntity> {
        val history = QAccountHistoryEntity.accountHistoryEntity
        val res = jpaQueryFactory.select(history).from(history).where(history.accountId.eq(accountId)).fetch()
        return res
    }

    override fun saveHistory(
        accountId: Int,
        amount: Int,
        difference: Int,
        transactionAccountId: Int
    ): AccountHistoryEntity {
        val historyEntity =
            AccountHistoryEntity(null, accountId, amount, difference, transactionAccountId, LocalDateTime.now())
        entityManager.persist(historyEntity);

        return historyEntity;
    }
}