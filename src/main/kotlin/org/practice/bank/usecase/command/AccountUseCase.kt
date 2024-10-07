package org.practice.bank.usecase.command

import org.practice.bank.domains.account.dto.DepositAccountDto
import org.practice.bank.domains.account.dto.TransferAccountDto
import org.practice.bank.domains.account.dto.WithdrawalAccountDto
import org.practice.bank.domains.account.service.AccountService
import org.practice.bank.usecase.command.dto.DepositCommand
import org.practice.bank.usecase.command.dto.TransferCommand
import org.practice.bank.usecase.command.dto.WithdrawalCommand
import org.springframework.stereotype.Service

@Service
class AccountUseCase(
    val accountService: AccountService
) {
    fun deposit(command: DepositCommand) {
        accountService.deposit(
            DepositAccountDto(
                command.accountId,
                command.money
            )
        )
    }

    fun withdrawal(command: WithdrawalCommand) {
        accountService.withdrawal(
            WithdrawalAccountDto(
                command.accountId,
                command.money
            )
        )
    }

    fun transfer(command: TransferCommand) {
        accountService.transfer(
            TransferAccountDto(
                command.accountId,
                command.money,
                command.targetAccountId
            )
        )
    }
}