package org.practice.bank.domains.user.factory

import org.practice.bank.domains.user.model.User
import org.springframework.stereotype.Component

@Component
class UserFactory {
    fun create(userName: String, password: String): User {
        if (password.length < 10) {
            throw Exception("Password must be at least 10 characters long")
        }
        return User(
            null, // 또는 적절한 기본값 설정
            userName,
            password
        )
    }
}