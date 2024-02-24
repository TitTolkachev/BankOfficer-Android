package com.example.trbofficerandroid.presentation.ui.screen.client

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun ClientScreen() {
    ClientScreenContent()
}

@Composable
private fun ClientScreenContent() {
    Text("TODO: Экран с информацией о клиенте")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        ClientScreenContent()
    }
}