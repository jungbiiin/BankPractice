package org.practice.bank.domains.account.repository

import org.practice.bank.domains.account.model.Account

interface AccountRepository {

    fun save(account: Account)

    fun findById(accountId: Int): Account?

}