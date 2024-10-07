package org.practice.bank.domains.account.dto

import org.practice.bank.domains.common.vo.Money

class WithdrawalAccountDto (
    val accountId: Int,
    val money: Money
) {
    init {
        if(accountId <= 0){
            throw Exception("Invalid account id")
        }
        if(money.amount <= 0) {
            throw Exception("money must be greater than zero!")
        }
    }
}