package org.practice.bank.domains.account.service

import org.practice.bank.domains.account.dto.AddAccountMoneyDto
import org.practice.bank.domains.account.dto.CreateAccountDto
import org.practice.bank.domains.account.repository.AccountRepository
import org.practice.bank.domains.account.event.AddedAccountBalanceEvent
import org.practice.bank.domains.account.factory.AccountFactory
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
    fun create(createAccount: CreateAccountDto) {
        val account = accountFactory.create(createAccount.userId)
        accountRepository.save(account)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun addMoney(addAccountMoney: AddAccountMoneyDto) {
        val account = accountRepository.findById(addAccountMoney.accountId)
            ?: throw Exception("not found account by accountId: ${addAccountMoney.accountId}")
        val oldBalance = account.balance
        account.addMoney(addAccountMoney.money)
        accountRepository.save(account)
        eventPublisher.publishEvent(
            AddedAccountBalanceEvent(
                account.id!!,
                account.userId,
                oldBalance,
                account.balance,
                addAccountMoney.money,
                LocalDateTime.now()
            )
        )
    }

}