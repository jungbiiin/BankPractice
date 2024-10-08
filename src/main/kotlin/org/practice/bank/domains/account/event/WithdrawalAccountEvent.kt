package org.practice.bank.domains.account.event

import org.practice.bank.domains.common.vo.Money
import java.time.LocalDateTime

class WithdrawalAccountEvent (
    val accountId: Int,
    val userId: Int,
    val oldBalance: Money,
    val newBalance: Money,
    val withdrawalMoney: Money,
    val createAt: LocalDateTime,
)