package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_account", schema = "db_bank")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    var id: Int?,
    @Column(name = "user_id", nullable = false)
    val userId: Int,
    @Column(name = "balance", nullable = false)
    var balance: Int,

    @Column(name = "currency", nullable = false)
    val currency: String,
)