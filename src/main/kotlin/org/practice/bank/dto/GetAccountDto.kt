package org.practice.bank.dto

import com.querydsl.core.annotations.QueryProjection

class GetAccountDto @QueryProjection constructor(
    val accountId: Int,
    val balance: Int
)