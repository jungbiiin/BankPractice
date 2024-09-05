package org.practice.bank.controller

import org.practice.bank.usecase.query.dto.GetAccountListRes
import org.practice.bank.controller.dto.LoginRequestDto
import org.practice.bank.domains.user.service.UserService
import org.practice.bank.usecase.query.AccountGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService,
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

}