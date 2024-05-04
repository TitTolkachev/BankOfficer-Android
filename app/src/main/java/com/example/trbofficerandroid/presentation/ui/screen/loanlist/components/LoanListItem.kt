package com.example.trbofficerandroid.presentation.ui.screen.loanlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.presentation.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LoanListItem(item: LoanShort, onClick: () -> Unit) {
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
            },
            supportingContent = {
                Column {
                    Text(
                        text = SimpleDateFormat(
                            "d MMMM yyyy",
                            Locale.getDefault()
                        ).format(Date(item.issuedDate))
                    )
                }
            },
            trailingContent = {
                Text(
                    text = item.amountDebt.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
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
            LoanListItem(
                item = LoanShort(
                    "123-123-123-123-123-123",
                    123123123123,
                    123123123123,
                    100,
                    15.0,
                ),
                onClick = {}
            )
        }
    }
}