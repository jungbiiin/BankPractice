package org.practice.bank.domains.account.event

import org.practice.bank.domains.common.vo.Money
import java.time.LocalDateTime

class TransferAccountEvent(
    val accountId: Int,
    val targetAccountId: Int,
    val userId: Int,
    val oldBalance: Money,
    val newBalance: Money,
    val targetOldBalance: Money,
    val targetNewBalance: Money,
    val transferMoney: Money,
    val createAt: LocalDateTime,
)