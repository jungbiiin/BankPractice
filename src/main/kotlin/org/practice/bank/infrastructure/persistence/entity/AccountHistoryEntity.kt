package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_account_history", schema = "db_bank")
class AccountHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_history_id", nullable = false)
    var id: Int?,
    @Column(name = "account_id", nullable = false)
    val accountId: Int,
    @Column(name = "amount", nullable = false)
    val amount: Int,
    //null 이면 단순 입출금
    @Column(name = "transaction_account_id", nullable = true)
    val transactionAccountId: Int?,
)