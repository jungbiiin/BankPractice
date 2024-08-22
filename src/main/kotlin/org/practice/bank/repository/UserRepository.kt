package org.practice.bank.repository

import org.practice.bank.dto.GetAccountDto

interface UserRepository {

    fun createUser(userName: String, password: String): Int

    fun getAccountList(): List<GetAccountDto>

}
