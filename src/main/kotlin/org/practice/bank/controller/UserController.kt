package org.practice.bank.controller

import org.practice.bank.usecase.query.dto.GetAccountListRes
import org.practice.bank.controller.dto.LoginRequestDto
import org.practice.bank.controller.dto.SignupRequestDto
import org.practice.bank.domains.user.service.UserService
import org.practice.bank.usecase.command.UserUseCase
import org.practice.bank.usecase.command.dto.SignupCommand
import org.practice.bank.usecase.query.AccountGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    val userUseCase: UserUseCase,
    val accountGateway: AccountGateway
) {

    @PostMapping
    fun login(@RequestBody user: LoginRequestDto): ResponseEntity<String> {
        return ResponseEntity("Login successful", HttpStatus.OK)
    }

    @GetMapping
    fun getAccountList(): ResponseEntity<GetAccountListRes> {
        return ResponseEntity.ok(
            GetAccountListRes(
                accountGateway.getAccountList()
            )
        )
    }

    @PostMapping("/signup")
    fun signup(@RequestBody user: SignupRequestDto): ResponseEntity<Int> {
        // 에러 처리는 어떻게 하는지?
        // 이게 맞는지?
        // usecase 랑 controller랑 1대 1 대응이 맞냐
        return ResponseEntity.ok(
            userUseCase.signup(SignupCommand(user.username,user.password))
        )
    }

}