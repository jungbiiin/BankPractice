package org.practice.bank.service

import org.junit.jupiter.api.Test

import org.practice.bank.BankApplication
import org.practice.bank.domains.account.dto.DepositAccountDto
import org.practice.bank.domains.account.dto.CreateAccountDto
import org.practice.bank.domains.account.dto.TransferAccountDto
import org.practice.bank.domains.account.dto.WithdrawalAccountDto
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
    fun deposit() {
        val res = accountService.create(CreateAccountDto(1))
        val addAccountMoney = DepositAccountDto(res.id!!, Money(10000, "KRW"))
        accountService.deposit(addAccountMoney)

        val account1 = accountRepository.findById(res.id!!)

        assertEquals(1010000, account1?.balance?.amount)
    }

    @Test
    fun deposit2() {
        val res = accountService.create(CreateAccountDto(1))
        val addAccountMoney = DepositAccountDto(res.id!!, Money(10000, "KRW"))
        accountService.deposit(addAccountMoney)

        val histories = accountHistoryRepository.getHistoriesByAccount(res.id!!);

        assertEquals(1, histories.size)
        assertEquals(10000, histories[0].difference)
    }

    @Test
    @Transactional
    fun withdrawal() {
        val res = accountService.create(CreateAccountDto(1))
        val withdrawalAccountMoney = WithdrawalAccountDto(res.id!!, Money(10000, "KRW"))
        accountService.withdrawal(withdrawalAccountMoney)

        val account1 = accountRepository.findById(res.id!!)

        assertEquals(990000, account1?.balance?.amount)
    }

    @Test
    fun withdrawal2() {
        val res = accountService.create(CreateAccountDto(1))
        val withdrawalAccountMoney = WithdrawalAccountDto(res.id!!, Money(10000, "KRW"))
        accountService.withdrawal(withdrawalAccountMoney)

        val histories = accountHistoryRepository.getHistoriesByAccount(res.id!!);

        assertEquals(1, histories.size)
        assertEquals(-10000, histories[0].difference)
    }

    @Test
    @Transactional
    fun transfer() {
        val account = accountService.create(CreateAccountDto(1))
        val targetAccount = accountService.create(CreateAccountDto(1))
        val transferAccountMoney = TransferAccountDto(account.id!!, Money(10000, "KRW"), targetAccount.id!!)
        accountService.transfer(transferAccountMoney)
        val accountRes = accountRepository.findById(account.id!!)
        val targetAccountRes = accountRepository.findById(targetAccount.id!!)
        assertEquals(990000, accountRes?.balance?.amount)
        assertEquals(1010000, targetAccountRes?.balance?.amount)
    }

    @Test
    fun transfer2() {
        val account = accountService.create(CreateAccountDto(1))
        val targetAccount = accountService.create(CreateAccountDto(1))

        val transferAccountMoney = TransferAccountDto(account.id!!, Money(10000, "KRW"), targetAccount.id!!)
        accountService.transfer(transferAccountMoney)

        val histories = accountHistoryRepository.getHistoriesByAccount(account.id!!);
        val targetHistories = accountHistoryRepository.getHistoriesByAccount(targetAccount.id!!);

        assertEquals(1, histories.size)
        assertEquals(-10000, histories[0].difference)
        assertEquals(10000, targetHistories[0].difference)
    }
}