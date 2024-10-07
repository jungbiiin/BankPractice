package org.practice.bank.usecase.command.dto

import org.practice.bank.domains.common.vo.Money

class TransferCommand (
    val accountId: Int,
    val money: Money,
    val targetAccountId: Int
)
