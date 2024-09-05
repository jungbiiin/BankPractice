package org.practice.bank.domains.account.model

import org.practice.bank.domains.common.vo.Money

class Account(
    var id: Int? = null,
    val userId: Int,
    var balance: Money
) {

    fun addMoney(amount: Money) {
        if(amount.currency != balance.currency) {
            throw Exception("")
        }
        this.balance += amount
    }

    fun subAmount(amount: Money) {
        this.balance -= amount
    }

}