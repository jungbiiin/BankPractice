package org.practice.bank.usecase.command

import org.practice.bank.domains.user.dto.CreateUserDto
import org.practice.bank.domains.user.service.UserService
import org.practice.bank.usecase.command.dto.SignupCommand

class UserUseCase(
    //  얘는 왜  private?
    private val userService: UserService
) {
    fun signup(command: SignupCommand) {
        userService.create(
            CreateUserDto(
                command.userName,
                command.password,
            )
        )
    }
}