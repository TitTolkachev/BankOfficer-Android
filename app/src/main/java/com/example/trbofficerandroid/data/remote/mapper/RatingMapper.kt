package com.example.trbofficerandroid.data.remote.mapper

import com.example.trbofficerandroid.domain.model.CreditRating
import com.example.trbofficerandroid.CreditRating as CreditRatingProto

object RatingMapper {

    fun CreditRatingProto.toDomain(): CreditRating {
        return CreditRating(
            calculationDate = calculationDate,
            rating = rating,
        )
    }
}