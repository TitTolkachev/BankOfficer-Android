package com.example.trbofficerandroid.presentation.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun MainScreen() {
    MainScreenContent()
}

@Composable
private fun MainScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("TODO", style = MaterialTheme.typography.titleLarge)
        Text("Экран с полезной информацией")
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MainScreenContent()
    }
}