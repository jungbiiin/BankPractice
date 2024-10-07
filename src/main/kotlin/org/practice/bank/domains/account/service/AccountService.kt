package org.practice.bank.domains.account.service

import org.practice.bank.domains.account.dto.DepositAccountDto
import org.practice.bank.domains.account.dto.CreateAccountDto
import org.practice.bank.domains.account.dto.TransferAccountDto
import org.practice.bank.domains.account.dto.WithdrawalAccountDto
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.account.event.DepositAccountEvent
import org.practice.bank.domains.account.event.TransferAccountEvent
import org.practice.bank.domains.account.event.WithdrawalAccountEvent
import org.practice.bank.domains.account.factory.AccountFactory
import org.practice.bank.domains.account.model.Account
import org.practice.bank.domains.common.vo.Money
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AccountService(
    val accountFactory: AccountFactory,
    val accountRepository: AccountRepository,
    val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun create(createAccount: CreateAccountDto): Account {
        val account = accountFactory.create(createAccount.userId)
        val res = accountRepository.save(account)

        return Account(res.id, res.userId, Money(res.balance, res.currency));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun deposit(depositMoney: DepositAccountDto) {
        val account = accountRepository.findById(depositMoney.accountId)
            ?: throw Exception("not found account by accountId: ${depositMoney.accountId}")
        val oldBalance = account.balance
        account.addMoney(depositMoney.money)
        accountRepository.save(account)
        eventPublisher.publishEvent(
            DepositAccountEvent(
                account.id!!,
                account.userId,
                oldBalance,
                account.balance,
                depositMoney.money,
                LocalDateTime.now()
            )
        )
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun withdrawal(withdrawalMoney: WithdrawalAccountDto){
        val account = accountRepository.findById(withdrawalMoney.accountId)
            ?: throw Exception("not found account by accountId: ${withdrawalMoney.accountId}")
        val oldBalance = account.balance
        account.subAmount(withdrawalMoney.money)
        accountRepository.save(account)
        eventPublisher.publishEvent(
            WithdrawalAccountEvent(
                account.id!!,
                account.userId,
                oldBalance,
                account.balance,
                withdrawalMoney.money,
                LocalDateTime.now()
            )
        )
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun transfer(transferMoney: TransferAccountDto){
        val account = accountRepository.findById(transferMoney.accountId)
        ?: throw Exception("not found account by accountId: ${transferMoney.accountId}")
        val targetAccount = accountRepository.findById(transferMoney.targetAccountId)
        ?: throw Exception("not found account by accountId: ${transferMoney.accountId}")
        val oldBalance = account.balance
        val oldTargetBalance = targetAccount.balance
        account.subAmount(transferMoney.money)
        targetAccount.addMoney(transferMoney.money)
        accountRepository.save(account)
        accountRepository.save(targetAccount)
        eventPublisher.publishEvent(
            TransferAccountEvent(
                account.id!!,
                targetAccount.id!!,
                account.userId,
                oldBalance,
                account.balance,
                oldTargetBalance,
                targetAccount.balance,
                transferMoney.money,
                LocalDateTime.now()
            )
        )
    }
}