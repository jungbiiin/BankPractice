package org.practice.bank.infrastructure.persistence.gateway

import com.querydsl.jpa.impl.JPAQueryFactory
import org.practice.bank.usecase.query.dto.GetAccountDto
import org.practice.bank.infrastructure.persistence.entity.QAccountEntity.accountEntity
import org.practice.bank.usecase.query.AccountGateway
import org.practice.bank.usecase.query.dto.QGetAccountDto
import org.springframework.stereotype.Repository

@Repository
class AccountGateImpl(
    val jpaQueryFactory: JPAQueryFactory,
) : AccountGateway {

    override fun getAccountList(): List<GetAccountDto> {
        return jpaQueryFactory.select(
            QGetAccountDto(
                accountEntity.id,
                accountEntity.balance
            )
        ).from(accountEntity)
            .fetch()
    }

    override fun getUserIdList(): List<Int> {
        TODO("Not yet implemented")
    }

}