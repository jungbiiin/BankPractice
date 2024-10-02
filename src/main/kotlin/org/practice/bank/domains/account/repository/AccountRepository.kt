package org.practice.bank.domains.account.repository

import org.practice.bank.domains.account.model.Account
import org.practice.bank.infrastructure.persistence.entity.AccountEntity

interface AccountRepository {

    fun save(account: Account):AccountEntity

    fun findById(accountId: Int): Account?

    fun findByUserId(userId: Int): List<Account>
}