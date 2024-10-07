package org.practice.bank.domains.account.event

import org.practice.bank.domains.common.vo.Money
import java.time.LocalDateTime

class DepositAccountEvent(
    val accountId: Int,
    val userId: Int,
    val oldBalance: Money,
    val newBalance: Money,
    val addMoney: Money,
    val createAt: LocalDateTime,
)