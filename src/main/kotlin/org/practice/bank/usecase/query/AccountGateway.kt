package org.practice.bank.usecase.query

import org.practice.bank.usecase.query.dto.GetAccountDto

interface AccountGateway {

    fun getAccountList(): List<GetAccountDto>

    fun getUserIdList(): List<Int>

}