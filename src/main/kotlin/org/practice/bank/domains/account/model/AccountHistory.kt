package org.practice.bank.domains.account.model

import jakarta.persistence.Column
import org.practice.bank.domains.common.vo.Money
import java.time.LocalDateTime

class AccountHistory (
    var id: Int?,
    val accountId: Int,
    val amount: Int,
    val difference: Int,
    val transactionAccountId: Int,
    val createDateTime: LocalDateTime,
)