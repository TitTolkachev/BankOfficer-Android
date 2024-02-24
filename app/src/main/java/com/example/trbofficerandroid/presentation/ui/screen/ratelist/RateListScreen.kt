package com.example.trbofficerandroid.presentation.ui.screen.ratelist

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun RateListScreen() {
    RateListScreenContent()
}

@Composable
private fun RateListScreenContent() {
    Text("TODO: Экран просмотра списка тарифов по кредитам")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            RateListScreenContent()
        }
    }
}