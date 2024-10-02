package org.practice.bank.domains.account.repository

import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity

interface AccountHistoryRepository {
    fun getHistoriesByAccount(accountId: Int): List<AccountHistoryEntity>
    fun saveHistory(accountId: Int, amount: Int, difference: Int, transactionAccountId: Int): AccountHistoryEntity
}