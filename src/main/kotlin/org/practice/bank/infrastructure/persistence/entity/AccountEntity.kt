package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_account", schema = "db_bank")
class AccountEntity(
    @Id
    @Column(name = "account_id", nullable = false)
    var id: Int?,
    @Column(name = "user_id", nullable = false)
    val userId: Int,
    @Column(name = "balance", nullable = false)
    val balance: Int,
)