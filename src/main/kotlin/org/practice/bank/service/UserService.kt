package org.practice.bank.service

import org.practice.bank.dto.GetAccountDto
import org.practice.bank.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAccountList(userId: Int): List<GetAccountDto> {
        return userRepository.getAccountList(userId)
    }

    @Transactional
    fun createUser() {
    }

}
