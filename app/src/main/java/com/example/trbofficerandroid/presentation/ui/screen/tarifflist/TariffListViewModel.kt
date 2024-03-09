package com.example.trbofficerandroid.presentation.ui.screen.tarifflist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.domain.usecase.GetTariffListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TariffListViewModel(
    private val getTariffListUseCase: GetTariffListUseCase
) : ViewModel() {

    private val _tariffList: MutableStateFlow<List<Tariff>?> = MutableStateFlow(null)
    val tariffList: StateFlow<List<Tariff>?> = _tariffList.asStateFlow()

    init {
        loadData()
    }

    suspend fun loadTariffs() {
        val list = try {
            getTariffListUseCase()
        } catch (_: Exception) {
            emptyList()
        }
        _tariffList.update { list }
    }

    private fun loadData() {
        viewModelScope.launch { loadTariffs() }
    }
}