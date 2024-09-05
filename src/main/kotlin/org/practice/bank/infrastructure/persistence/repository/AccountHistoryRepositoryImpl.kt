package org.practice.bank.infrastructure.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity
import org.practice.bank.infrastructure.persistence.entity.QAccountHistoryEntity
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class AccountHistoryRepositoryImpl(
    val jpaQueryFactory: JPAQueryFactory,
) : AccountHistoryRepository {
    override fun getHistoriesByAccount(accountId: Int): List<AccountHistoryEntity> {
        val history = QAccountHistoryEntity.accountHistoryEntity
        val res = jpaQueryFactory.select(history).from(history).where(history.accountId.eq(accountId)).fetch()
        return res
    }
}