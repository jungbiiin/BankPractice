package org.practice.bank.domains.account

data class Money(
    val amount: Int,
    val currency: String,
) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount must be non-negative")
        }
    }

    operator fun plus(other: Money): Money {
        if(this.currency != other.currency) {
            throw Exception("Currency different")
        }

        return Money(this.amount + other.amount, this.currency)
    }

    operator fun minus(other: Money): Money {
        if(this.currency != other.currency) {
            throw Exception("Currency different")
        }

        return Money(this.amount - other.amount, this.currency)
    }

    companion object {
        fun krw(amount: Int): Money {
            return Money(amount, "KRW")
        }
        fun jpn(amount: Int): Money {
            return Money(amount, "JPN")
        }
    }
}