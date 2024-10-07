package org.practice.bank.domains.account.service

import org.practice.bank.domains.account.event.DepositAccountEvent
import org.practice.bank.domains.account.event.WithdrawalAccountEvent
import org.practice.bank.domains.account.repository.AccountHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class AccountHistoryService(
    val accountHistoryRepository: AccountHistoryRepository,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deposit(event: DepositAccountEvent) {
        accountHistoryRepository.save(
            event.accountId,
            event.newBalance.amount,
            event.newBalance.amount - event.oldBalance.amount,
            event.accountId
        )
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun withdrawal(event: WithdrawalAccountEvent) {
        accountHistoryRepository.save(
            event.accountId,
            event.newBalance.amount,
            event.newBalance.amount - event.oldBalance.amount,
            event.accountId
        )
    }
}