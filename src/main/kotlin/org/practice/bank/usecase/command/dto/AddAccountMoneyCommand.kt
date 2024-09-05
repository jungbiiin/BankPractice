package org.practice.bank.usecase.command.dto

import org.practice.bank.domains.common.vo.Money

class AddAccountMoneyCommand(
    val accountId: Int,
    val money: Money
)
