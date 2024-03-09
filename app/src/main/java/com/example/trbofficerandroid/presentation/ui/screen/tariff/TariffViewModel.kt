package com.example.trbofficerandroid.presentation.ui.screen.tariff

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TariffViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val id = savedStateHandle["id"] ?: "UNKNOWN"
    val additionDate = savedStateHandle.get<Long?>("additionDate")?.let {
        SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(Date(it))
    } ?: "UNKNOWN"
    val name = savedStateHandle["name"] ?: "UNKNOWN"
    val description = savedStateHandle["description"] ?: "UNKNOWN"
    val interestRate = savedStateHandle.get<Float?>("interestRate")?.let { "$it%" } ?: "UNKNOWN"
    val officerId = savedStateHandle.get<String?>("officerId")
}