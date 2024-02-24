package com.example.hbofficerandroid.presentation.ui.screen.adduser

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hbofficerandroid.presentation.theme.AppTheme

@Composable
fun AddUserScreen() {
    AddUser()
}

@Composable
private fun AddUser() {
    Text("TODO: Экран добавления пользователя")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AddUser()
        }
    }
}