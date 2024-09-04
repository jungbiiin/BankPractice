package org.practice.bank.repository

import org.practice.bank.domains.account.Money
import org.practice.bank.infrastructure.persistence.entity.AccountEntity
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity

interface AccountRepository {
    fun createAccount(userId: Int, currency: String): AccountEntity
    fun putMoney(accountId: Int, money: Money): AccountHistoryEntity
    fun getHistory(userId: Int): List<AccountHistoryEntity>
    fun deleteAccount(userId: Int)
    fun getUserAccount(userId: Int): List<AccountEntity>
}