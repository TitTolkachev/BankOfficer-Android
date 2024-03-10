package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetLoanListReply
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.domain.model.LoanStatus

object LoanMapper {

    fun GetLoanListReply.toDomain(): List<LoanShort> {
        return this.loanList.map { it.toDomain() }
    }

    fun com.example.trbofficerandroid.LoanShort.toDomain(): LoanShort {
        return LoanShort(
            id = id,
            clientFirstName = clientFirstName,
            clientLastName = clientLastName,
            issuedAmount = issuedAmount,
            amountDept = amountDept,
            status = if (status == LoanStatus.OPEN.name) LoanStatus.OPEN else LoanStatus.CLOSED,
        )
    }
}