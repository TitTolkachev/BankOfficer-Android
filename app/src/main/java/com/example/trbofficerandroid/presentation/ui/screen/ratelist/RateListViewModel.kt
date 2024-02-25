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
                    percentageRate = 15.5
                ),
                RateShort(
                    id = "2",
                    name = "Лучший февральский тариф",
                    percentageRate = 16.5
                ),
                RateShort(
                    id = "3",
                    name = "Лучший мартовский тариф",
                    percentageRate = 10.5
                ),
                RateShort(
                    id = "4",
                    name = "Лучший апрельский тариф",
                    percentageRate = 11.5
                ),
                RateShort(
                    id = "5",
                    name = "Семейный",
                    percentageRate = 1.5
                ),
            )
        }
    }
}