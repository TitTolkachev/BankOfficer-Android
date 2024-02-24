package com.example.trbofficerandroid.presentation.ui.screen.officer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun OfficerScreen() {
    OfficerScreenContent()
}

@Composable
private fun OfficerScreenContent() {
    Text("TODO: Экран с информацией о сотруднике")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        OfficerScreenContent()
    }
}