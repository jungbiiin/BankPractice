package org.practice.bank.repository

import org.practice.bank.dto.GetAccountDto
import org.practice.bank.infrastructure.persistence.entity.UserEntity
import org.springframework.stereotype.Repository

interface UserRepository {

    fun createUser(userName: String, password: String): UserEntity

    fun getAccountList(): List<GetAccountDto>

    fun getUserIdList(): List<Int>
}
