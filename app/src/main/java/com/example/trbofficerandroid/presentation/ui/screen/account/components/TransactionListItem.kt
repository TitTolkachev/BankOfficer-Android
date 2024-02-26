package com.example.trbofficerandroid.presentation.ui.screen.account.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.screen.account.model.TransactionShort
import com.example.trbofficerandroid.presentation.ui.screen.account.model.TransactionType.WITHDRAWAL

@Composable
fun TransactionListItem(item: TransactionShort, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .clickable(onClick = onClick)
    ) {
        ListItem(
            headlineContent = {
                Column {
                    Text(text = item.id)
                }
            }
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            TransactionListItem(
                TransactionShort(
                    id = "4124125125",
                    type = WITHDRAWAL,
                ),
            )
        }
    }
}