package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

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
    @Column(name = "difference", nullable = false)
    val difference: Int,
    //id와 같으면 이면 단순 입출금
    @Column(name = "transaction_account_id", nullable = false)
    val transactionAccountId: Int,
    @Column(name = "create_datetime", nullable = false)
    val createDateTime: LocalDateTime = LocalDateTime.now()
)