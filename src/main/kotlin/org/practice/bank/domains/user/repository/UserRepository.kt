package org.practice.bank.domains.user.repository

import org.practice.bank.domains.user.model.User

interface UserRepository {

    fun save(user: User): User
}
