package org.practice.bank.usecase.command.dto

import org.practice.bank.domains.common.vo.Money

class WithdrawalCommand (
    val accountId: Int,
    val money: Money
)