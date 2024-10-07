package org.practice.bank.domains.user.service

import org.practice.bank.domains.user.dto.CreateUserDto
import org.practice.bank.domains.user.factory.UserFactory
import org.practice.bank.domains.user.model.User
import org.practice.bank.domains.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userFactory: UserFactory
) {

    @Transactional
    fun create(createUser: CreateUserDto): User {
        val user = userFactory.create(createUser.userName, createUser.password)
        val res = userRepository.save(User(null,user.name,user.password))

        return res
    }

}
