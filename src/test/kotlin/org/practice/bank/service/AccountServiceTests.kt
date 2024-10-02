package org.practice.bank.service

import org.junit.jupiter.api.Test

import org.practice.bank.BankApplication
import org.practice.bank.domains.account.dto.AddAccountMoneyDto
import org.practice.bank.domains.account.dto.CreateAccountDto
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.account.service.AccountService
import org.practice.bank.domains.common.vo.Money
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@SpringBootTest
@ContextConfiguration(classes = [BankApplication::class])
class AccountServiceTests {

    @Autowired(required = true)
    private lateinit var accountService: AccountService

    @Autowired(required = true)
    private lateinit var accountRepository: AccountRepository

    @Autowired(required = true)
    private lateinit var accountHistoryRepository: AccountHistoryRepository


    @Test
    fun create() {
        val res = accountService.create(CreateAccountDto(1));
        assertEquals(1, res.userId)
        assertEquals(1000000, res.balance.amount)
        assertEquals("KRW", res.balance.currency)
    }

    @Test
    @Transactional
    fun addMoney() {
        val res = accountService.create(CreateAccountDto(1));
        val addAccountMoney = AddAccountMoneyDto(res.id!!, Money(10000, "KRW"));
        accountService.addMoney(addAccountMoney);

        val account1 = accountRepository.findById(res.id!!);

        assertEquals(1010000, account1?.balance?.amount);
    }

    @Test
    fun addMoney2() {
        val res = accountService.create(CreateAccountDto(1));
        val addAccountMoney = AddAccountMoneyDto(res.id!!, Money(10000, "KRW"));
        accountService.addMoney(addAccountMoney);

        val histories = accountHistoryRepository.getHistoriesByAccount(res.id!!);

        assertEquals(1, histories.size)
        assertEquals(10000, histories[0].difference)
    }
}