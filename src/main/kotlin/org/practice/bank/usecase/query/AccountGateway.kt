package org.practice.bank.usecase.query

import org.practice.bank.usecase.query.dto.GetAccountDto

// 얘는 하는게 뭐야
interface AccountGateway {

    fun getAccountList(): List<GetAccountDto>

    fun getUserIdList(): List<Int>

}