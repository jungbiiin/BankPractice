package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.account.model.AccountHistory
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity
import org.practice.bank.infrastructure.persistence.entity.QAccountHistoryEntity
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.practice.bank.domains.common.vo.Money
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AccountHistoryRepositoryImpl(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory,
) : AccountHistoryRepository {
    override fun save(
        history: AccountHistory,
    ) {
        val historyEntity =
            AccountHistoryEntity(
                null,
                history.accountId,
                history.amount,
                history.difference,
                history.transactionAccountId,
                LocalDateTime.now()
            )
        entityManager.persist(historyEntity);
    }

    override fun getHistoriesByAccount(accountId: Int): List<AccountHistory> {
        val history = QAccountHistoryEntity.accountHistoryEntity
        val res = jpaQueryFactory.select(history).from(history).where(history.accountId.eq(accountId)).fetch()

        return res.map { historyEntity ->
            AccountHistory(
                historyEntity.id,
                historyEntity.accountId,
                historyEntity.amount,
                historyEntity.difference,
                historyEntity.transactionAccountId,
                historyEntity.createDateTime
            )
        }
    }
}