package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_user", schema = "db_bank")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Int?,
    @Column(name = "user_name", nullable = false, length = 50)
    val userName: String,
    @Column(name = "user_password", nullable = false, length = 50)
    val userPassword: String,
)