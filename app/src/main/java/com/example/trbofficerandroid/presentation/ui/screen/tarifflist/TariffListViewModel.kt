package com.example.trbofficerandroid.presentation.ui.screen.tarifflist

import androidx.lifecycle.ViewModel
import com.example.trbofficerandroid.presentation.ui.screen.tarifflist.model.TariffShort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TariffListViewModel : ViewModel() {

    private val _tariffList: MutableStateFlow<List<TariffShort>?> = MutableStateFlow(null)
    val tariffList: StateFlow<List<TariffShort>?> = _tariffList.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _tariffList.update {
            listOf(
                TariffShort(
                    id = "1",
                    name = "Обычный тариф",
                    interestRate = 15.5
                ),
                TariffShort(
                    id = "2",
                    name = "Лучший февральский тариф",
                    interestRate = 16.5
                ),
                TariffShort(
                    id = "3",
                    name = "Лучший мартовский тариф",
                    interestRate = 10.5
                ),
                TariffShort(
                    id = "4",
                    name = "Лучший апрельский тариф",
                    interestRate = 11.5
                ),
                TariffShort(
                    id = "5",
                    name = "Семейный",
                    interestRate = 1.5
                ),
            )
        }
    }
}