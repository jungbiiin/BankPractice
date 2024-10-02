package org.practice.bank.domains.account.service

import org.practice.bank.domains.account.event.AddedAccountBalanceEvent
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
    fun addMoney(event: AddedAccountBalanceEvent) {
        System.out.println("엄마 저 히스토리 안이에요")
        accountHistoryRepository.saveHistory(
            event.accountId,
            event.newBalance.amount,
            event.newBalance.amount - event.oldBalance.amount,
            event.accountId
        )
    }

}