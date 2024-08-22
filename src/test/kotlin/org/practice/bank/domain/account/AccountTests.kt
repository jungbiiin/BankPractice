import org.junit.jupiter.api.assertThrows
import org.practice.bank.domains.account.Account
import org.practice.bank.domains.account.Money
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountTests {
    @Test
    fun addKRWAmount() {
        val account = Account()
        account.addAmount(Money.krw(1000))
        assertEquals(Money.krw(1000), account.amount)
    }

    @Test
    fun subKRWAmount() {
        val account = Account()
        account.addAmount(Money.krw(1000))
        account.subAmount(Money.krw(500))
        assertEquals(Money.krw(500), account.amount)
    }

    @Test
    fun addJPNAmount() {
        val account = Account()
        assertThrows<Exception> {
            account.addAmount(Money.jpn(100))
        }
    }

    fun addMonetaryAmount() {

    }
}
