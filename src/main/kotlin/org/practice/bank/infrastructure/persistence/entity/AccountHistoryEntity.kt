package org.practice.bank.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.practice.bank.infrastructure.persistence.entity.time.BaseEntity
import org.springframework.data.annotation.CreatedDate

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
    // null인경우 단순 입출금
    @Column(name = "transaction_account_id", nullable = true)
    val transactionAccountId: Int?,
): BaseEntity() //애초에 이렇게 하는게 맞는 건지