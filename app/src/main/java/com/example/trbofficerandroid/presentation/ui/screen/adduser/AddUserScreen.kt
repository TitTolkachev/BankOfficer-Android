package com.example.trbofficerandroid.presentation.ui.screen.adduser

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun AddUserScreen() {
    AddUserScreenContent()
}

@Composable
private fun AddUserScreenContent() {
    Text("TODO: Экран добавления пользователя")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AddUserScreenContent()
        }
    }
}