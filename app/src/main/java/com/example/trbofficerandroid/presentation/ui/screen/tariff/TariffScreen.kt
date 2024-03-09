package com.example.trbofficerandroid.presentation.ui.screen.tariff

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun TariffScreen() {
    TariffScreenContent()
}

@Composable
private fun TariffScreenContent() {
    Text("TODO: Экран просмотра тарифа по кредиту")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            TariffScreenContent()
        }
    }
}