package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.Application
import com.example.trbofficerandroid.ApplicationState
import com.example.trbofficerandroid.GetApplicationListReply
import com.example.trbofficerandroid.data.remote.mapper.TariffMapper.toDomain
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.AccountType
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.Account as AccountProto

object AccountMapper {

    fun List<AccountProto>.toDomain(): List<Account> {
        return this.map { it.toDomain() }
    }

    fun AccountProto.toDomain(): Account {
        return Account(
            id = id,
            type = AccountType.valueOf(type),
            balance = balance,
            currency = currency,
            clientFullName = clientFullName,
            externalClientId = externalClientId,
            creationDate = creationDate,
            closingDate = if (this.hasClosingDate()) closingDate else null,
            isClosed = isClosed,
        )
    }

    fun GetApplicationListReply.toDomain(): List<ApplicationShort> {
        return this.applicationList.map { it.toDomain() }
    }

    fun Application.toDomain(): com.example.trbofficerandroid.domain.model.Application {
        return com.example.trbofficerandroid.domain.model.Application(
            id = id,
            creationDate = creationDate,
            updatedDateFinal = if (hasUpdatedDateFinal()) updatedDateFinal else null,
            loanTermInDays = loanTermInDays,
            issuedAmount = issuedAmount,
            clientId = clientId,
            officerId = if (hasOfficerId()) officerId else null,
            state = state.toDomain(),
            tariff = tariff.toDomain()
        )
    }

    fun ApplicationState.toDomain(): com.example.trbofficerandroid.domain.model.ApplicationState {
        return com.example.trbofficerandroid.domain.model.ApplicationState.valueOf(this.name)
    }

    fun com.example.trbofficerandroid.ApplicationShort.toDomain(): ApplicationShort {
        return ApplicationShort(
            id = id,
            loanTermInDays = loanTermInDays,
            issuedAmount = issuedAmount,
            interestRate = interestRate
        )
    }
}