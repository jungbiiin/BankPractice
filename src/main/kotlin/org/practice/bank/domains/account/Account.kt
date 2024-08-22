package org.practice.bank.domains.account

class Account {
    var amount: Money = Money(0, "KRW")

    fun addAmount(amount: Money) {
        this.amount += amount
    }

    fun subAmount(amount: Money) {
        this.amount -= amount
    }
}