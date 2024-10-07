package org.practice.bank.usecase.command

import org.practice.bank.domains.user.dto.CreateUserDto
import org.practice.bank.domains.user.service.UserService
import org.practice.bank.usecase.command.dto.SignupCommand
import org.springframework.stereotype.Service

@Service
class UserUseCase(
    //  얘는 왜  private?
    private val userService: UserService
) {
    fun signup(command: SignupCommand): Int {
        val res = userService.create(
            CreateUserDto(
                command.userName,
                command.password,
            )
        )
        return res.id!!;
    }
}