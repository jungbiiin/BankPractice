package org.practice.bank.infrastructure.persistence.entity.time

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity{
    @CreatedDate
    @Column(name="create_datetime",nullable = false, updatable = false)
    var createDatetime: LocalDateTime = LocalDateTime.MIN
}
