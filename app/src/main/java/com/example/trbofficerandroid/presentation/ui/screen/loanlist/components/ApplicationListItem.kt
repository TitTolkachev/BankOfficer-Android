package com.example.trbofficerandroid.presentation.ui.screen.loanlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.presentation.theme.AppTheme

@Composable
fun ApplicationListItem(item: ApplicationShort, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ListItem(
                modifier = Modifier.weight(1f),
                headlineContent = {
                    Column {
                        Text(text = "${item.issuedAmount / 100}₽")
                    }
                },
                supportingContent = {
                    Column {
                        Text(text = "Срок: ${item.loanTermInDays}")
                    }
                }
            )
            Surface(
                shape = RoundedCornerShape(100),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),
                    text = "${item.interestRate}%"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            ApplicationListItem(
                ApplicationShort(
                    "1",
                    4,
                    100,
                    11.1
                )
            ) {}
        }
    }
}