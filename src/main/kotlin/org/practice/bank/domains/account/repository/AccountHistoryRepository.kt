package org.practice.bank.domains.account.repository

import org.practice.bank.domains.account.model.AccountHistory
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity

interface AccountHistoryRepository {
    fun getHistoriesByAccount(accountId: Int): List<AccountHistory>
    fun save(history: AccountHistory)
}