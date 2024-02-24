package com.example.trbofficerandroid.presentation.ui.screen.rate

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun RateScreen() {
    RateScreenContent()
}

@Composable
private fun RateScreenContent() {
    Text("TODO: Экран просмотра тарифа по кредиту")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            RateScreenContent()
        }
    }
}