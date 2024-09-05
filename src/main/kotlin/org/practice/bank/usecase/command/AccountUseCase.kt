package org.practice.bank.usecase.command

import org.practice.bank.domains.account.dto.AddAccountMoneyDto
import org.practice.bank.domains.account.service.AccountService
import org.practice.bank.usecase.command.dto.AddAccountMoneyCommand
import org.springframework.stereotype.Service

@Service
class AccountUseCase(
    val accountService: AccountService
) {

    fun addMoney(command: AddAccountMoneyCommand) {
        accountService.addMoney(
            AddAccountMoneyDto(
                command.accountId,
                command.money
            )
        )
    }

}