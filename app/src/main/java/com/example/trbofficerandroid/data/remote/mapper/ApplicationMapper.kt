package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetApplicationListReply
import com.example.trbofficerandroid.data.remote.mapper.TariffMapper.toDomain
import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.domain.model.ApplicationState
import com.example.trbofficerandroid.Application as ApplicationProto
import com.example.trbofficerandroid.ApplicationShort as ApplicationShortProto
import com.example.trbofficerandroid.ApplicationState as ApplicationStateProto

object ApplicationMapper {

    fun GetApplicationListReply.toDomain(): List<ApplicationShort> {
        return this.applicationList.map { it.toDomain() }
    }

    fun ApplicationProto.toDomain(): Application {
        return Application(
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

    fun ApplicationStateProto.toDomain(): ApplicationState {
        return ApplicationState.valueOf(this.name)
    }

    fun ApplicationShortProto.toDomain(): ApplicationShort {
        return ApplicationShort(
            id = id,
            loanTermInDays = loanTermInDays,
            issuedAmount = issuedAmount,
            interestRate = interestRate
        )
    }
}