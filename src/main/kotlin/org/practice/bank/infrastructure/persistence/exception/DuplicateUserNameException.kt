package org.practice.bank.infrastructure.persistence.exception

class DuplicateUserNameException(userName: String) : RuntimeException("Username $userName already exists") {
}
