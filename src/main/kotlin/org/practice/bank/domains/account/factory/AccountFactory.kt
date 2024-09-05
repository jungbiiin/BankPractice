package org.practice.bank.domains.account.factory

import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.common.vo.Money
import org.springframework.stereotype.Component

@Component
class AccountFactory {

    fun create(userId: Int): Account {
        return Account(
            null,
            userId,
            Money.krw(1000000)
        )
    }

}