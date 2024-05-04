package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetLoanListReply
import com.example.trbofficerandroid.data.remote.mapper.TariffMapper.toDomain
import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.model.LoanRepayment
import com.example.trbofficerandroid.domain.model.LoanRepaymentState
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.domain.model.LoanStatus
import com.example.trbofficerandroid.Loan as LoanProto
import com.example.trbofficerandroid.LoanRepayment as LoanRepaymentProto
import com.example.trbofficerandroid.LoanShort as LoanShortProto

object LoanMapper {

    fun GetLoanListReply.toDomain(): List<LoanShort> {
        return this.loansList.map { it.toDomain() }
    }

    fun LoanShortProto.toDomain(): LoanShort {
        return LoanShort(
            id = id,
            issuedDate = issuedDate,
            repaymentDate = repaymentDate,
            amountDebt = amountDebt,
            interestRate = interestRate,
        )
    }

    fun LoanProto.toDomain(): Loan {
        return Loan(
            id = id,
            issuedDate = issuedDate,
            repaymentDate = repaymentDate,
            issuedAmount = issuedAmount,
            amountDebt = amountDebt,
            accruedPenny = accruedPenny,
            loanTermInDays = loanTermInDays,
            clientId = clientId,
            accountId = accountId,
            state = LoanStatus.valueOf(state),
            tariff = tariff.toDomain(),
            repayments = repaymentsList.toDomain()
        )
    }

    fun List<LoanRepaymentProto>.toDomain(): List<LoanRepayment> {
        return this.map { it.toDomain() }
    }

    fun LoanRepaymentProto.toDomain(): LoanRepayment {
        return LoanRepayment(
            id = id,
            date = date,
            amount = amount,
            state = LoanRepaymentState.valueOf(state)
        )
    }
}