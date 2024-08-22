package org.practice.bank.controller

import org.practice.bank.dto.LoginRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {
    @PostMapping
    fun login(@RequestBody user: LoginRequestDto): ResponseEntity<String> {
        return ResponseEntity("Login successful", HttpStatus.OK)
    }
}