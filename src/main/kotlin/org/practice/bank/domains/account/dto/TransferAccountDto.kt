package org.practice.bank.domains.account.dto

import org.practice.bank.domains.common.vo.Money

class TransferAccountDto(
    val accountId: Int,
    val money: Money,
    val targetAccountId: Int
) {
    init {
        if (accountId == targetAccountId) {
            throw Exception("You can't transfer same account")
        }
        if (accountId <= 0) {
            throw Exception("Invalid accountId")
        }
        if (targetAccountId <= 0) {
            throw Exception("Invalid targetAccountId")
        }
        if (money.amount <= 0) {
            throw Exception("money must be greater than zero!")
        }
    }
}