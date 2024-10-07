package org.practice.bank.domains.account.service

import org.practice.bank.domains.account.event.DepositAccountEvent
import org.practice.bank.domains.account.event.TransferAccountEvent
import org.practice.bank.domains.account.event.WithdrawalAccountEvent
import org.practice.bank.domains.account.model.AccountHistory
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
            AccountHistory(
                null,
                event.accountId,
                event.newBalance.amount,
                event.newBalance.amount - event.oldBalance.amount,
                event.accountId,
                null
            )
        )
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun withdrawal(event: WithdrawalAccountEvent) {
        accountHistoryRepository.save(
            AccountHistory(
                null,
                event.accountId,
                event.newBalance.amount,
                event.newBalance.amount - event.oldBalance.amount,
                event.accountId,
                null
            )
        )
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun transfer(event: TransferAccountEvent) {
        accountHistoryRepository.save(
            AccountHistory(
                null,
                event.accountId,
                event.newBalance.amount,
                event.newBalance.amount - event.oldBalance.amount,
                event.targetAccountId,
                null
            )
        )
        accountHistoryRepository.save(
            AccountHistory(
                null,
                event.targetAccountId,
                event.targetNewBalance.amount,
                event.targetNewBalance.amount - event.targetOldBalance.amount,
                event.accountId,
                null,
            )
        )
    }
}