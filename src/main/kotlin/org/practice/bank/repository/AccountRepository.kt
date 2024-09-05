package org.practice.bank.repository

import org.practice.bank.domains.account.Money
import org.practice.bank.infrastructure.persistence.entity.AccountEntity
import org.practice.bank.infrastructure.persistence.entity.AccountHistoryEntity

interface AccountRepository {
    fun createAccount(userId: Int, currency: String): AccountEntity
    fun addMoney(accountId: Int, money: Money, transcationAccountId: Int?): AccountHistoryEntity
    fun subtractMoney(accountId: Int, money: Money, transcationAccountId: Int?): AccountHistoryEntity
    fun deleteAccount(userId: Int)
}