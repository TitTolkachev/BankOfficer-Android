package com.example.hbofficerandroid.presentation.ui.screen.more

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hbofficerandroid.presentation.theme.AppTheme

@Composable
fun MoreScreen() {
    MoreScreenContent()
}

@Composable
private fun MoreScreenContent() {
    Text("TODO: Экран со всем подряд")
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            MoreScreenContent()
        }
    }
}