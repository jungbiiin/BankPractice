package org.practice.bank.controller

import org.practice.bank.dto.GetAccountListRes
import org.practice.bank.dto.LoginRequestDto
import org.practice.bank.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService
) {

    @PostMapping
    fun login(@RequestBody user: LoginRequestDto): ResponseEntity<String> {
        return ResponseEntity("Login successful", HttpStatus.OK)
    }

//    @GetMapping
//    fun getAccountList(): ResponseEntity<GetAccountListRes> {
//        return ResponseEntity.ok(
//            GetAccountListRes(
//                userService.getAccountList()
//            )
//        )
//    }

}