package org.practice.bank.domains.user.repository

import org.practice.bank.infrastructure.persistence.entity.UserEntity

interface UserRepository {

    fun createUser(userName: String, password: String): UserEntity

}
