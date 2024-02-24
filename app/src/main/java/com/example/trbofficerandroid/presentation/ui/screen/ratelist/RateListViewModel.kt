package com.example.trbofficerandroid.presentation.ui.screen.ratelist

import androidx.lifecycle.ViewModel
import com.example.trbofficerandroid.presentation.ui.screen.ratelist.model.RateShort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RateListViewModel : ViewModel() {

    private val _rateList: MutableStateFlow<List<RateShort>?> = MutableStateFlow(null)
    val rateList: StateFlow<List<RateShort>?> = _rateList.asStateFlow()

    init {
        loadRateList()
    }

    private fun loadRateList() {
        _rateList.update {
            listOf(
                RateShort(
                    id = "1",
                    name = "Обычный тариф",
                    limit = "1000₽",
                    percentageRate = 15.5,
                    period = "5 лет"
                ),
                RateShort(
                    id = "2",
                    name = "Лучший февральский тариф",
                    limit = "100₽",
                    percentageRate = 16.5,
                    period = "3 месяца"
                ),
                RateShort(
                    id = "3",
                    name = "Лучший мартовский тариф",
                    limit = "10₽",
                    percentageRate = 10.5,
                    period = "1 месяц"
                ),
                RateShort(
                    id = "4",
                    name = "Лучший апрельский тариф",
                    limit = "1010₽",
                    percentageRate = 11.5,
                    period = "6 лет"
                ),
            )
        }
    }
}