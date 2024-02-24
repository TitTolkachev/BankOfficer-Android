package com.example.hbofficerandroid.presentation.ui.screen.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hbofficerandroid.presentation.theme.AppTheme

@Composable
fun MainScreen() {
    MainScreenContent()
}

@Composable
private fun MainScreenContent() {
    Text("TODO: Экран с полезной информацией")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MainScreenContent()
    }
}