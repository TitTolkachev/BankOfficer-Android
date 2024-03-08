package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.GetTariffListReply
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.Tariff as TariffProto

object TariffMapper {

    fun GetTariffListReply.toDomain(): List<Tariff> {
        return this.tariffsList.map { it.toDomain() }
    }

    fun TariffProto.toDomain(): Tariff {
        return Tariff(
            id = id,
            additionDate = additionDate,
            name = name,
            description = description,
            interestRate = interestRate,
            officerId = officerId
        )
    }
}