package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_account_history", schema = "db_bank")
class AccountHistoryEntity(
    @Id
    @Column(name = "account_history_id", nullable = false)
    var id: Int?,
    @Column(name = "account_id", nullable = false)
    val accountId: Int,
    @Column(name = "amount", nullable = false)
    val amount: Int,
    @Column(name = "create_datetime", nullable = false)
    val createDatetime: Int,
)