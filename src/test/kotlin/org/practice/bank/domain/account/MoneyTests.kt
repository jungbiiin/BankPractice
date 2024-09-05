package org.practice.bank.domain.account

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.practice.bank.domains.common.vo.Money
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MoneyTests {
    @Test
    fun createTest(){
        assertThrows<Exception> {
            Money.krw(-1000)
        }
        assertDoesNotThrow {
            Money.krw(1000)
        }
    }

    @Test
    fun equalsTest() {
        Money(1000,"KRW")
        val m1 = Money.krw(1000)
        val m2 = Money.krw(1000)
        val m3 = Money.krw(2000)
        assertEquals(m1, m2)
        assertNotEquals(m1, m3)
    }

    @Test
    fun addKRWMoney() {
        val money1 = Money.krw(1000)
        val money2 = Money.krw(1000)
        val result = money1 + money2
        assertEquals(result, Money.krw(2000))
    }

    @Test
    fun subtractKRWMoney() {
        val money1 = Money.krw(1000)
        val money2 = Money.krw(500)
        val result = money1 - money2
        assertEquals(result, Money.krw(500))
    }

    @Test
    fun addJPNMoney() {
        val money1 = Money.jpn(1000)
        val money2 = Money.jpn(1000)
        val result = money1 + money2
        assertEquals(result, Money.jpn(2000))
    }

    @Test
    fun subtractJPNMoney() {
        val money1 = Money.jpn(1000)
        val money2 = Money.jpn(500)
        val result = money1 - money2
        assertEquals(result, Money.jpn(500))
    }

    @Test
    fun addDiffCurrencyMoney() {
        val money1 = Money.krw(1000)
        val money2 = Money.jpn(1000)
        assertThrows<Exception> {
             money1 + money2
        }
    }

    @Test
    fun subDiffCurrencyMoney() {
        val money1 = Money.krw(1000)
        val money2 = Money.jpn(1000)
        assertThrows<Exception> {
            money1 - money2
        }
    }

}