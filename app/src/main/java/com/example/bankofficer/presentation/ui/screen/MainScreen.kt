package com.example.bankofficer.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankofficer.presentation.theme.AppTheme

@Composable
fun MainScreen() {
    Column(Modifier.padding(16.dp)) {
        repeat(5) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Text")
                    Spacer(Modifier.weight(1f))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Button")
                    }
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MainScreen()
    }
}