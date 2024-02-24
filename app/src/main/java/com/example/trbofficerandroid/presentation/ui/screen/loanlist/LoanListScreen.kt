package com.example.trbofficerandroid.presentation.ui.screen.loanlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun LoanListScreen() {
    LoanListScreenContent()
}

@Composable
private fun LoanListScreenContent() {
    Text("TODO: Экран с кредитами")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        LoanListScreenContent()
    }
}