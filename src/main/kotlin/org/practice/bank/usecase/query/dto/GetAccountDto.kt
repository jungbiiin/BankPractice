package org.practice.bank.usecase.query.dto

import com.querydsl.core.annotations.QueryProjection

class GetAccountDto @QueryProjection constructor(
    val accountId: Int,
    val balance: Int
)