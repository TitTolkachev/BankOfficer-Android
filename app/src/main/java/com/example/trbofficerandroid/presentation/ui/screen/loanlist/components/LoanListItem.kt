package com.example.trbofficerandroid.presentation.ui.screen.loanlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import com.example.trbofficerandroid.domain.model.LoanStatus
import com.example.trbofficerandroid.presentation.theme.AppTheme

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
                    Text(text = item.clientLastName)
                    Text(text = item.clientFirstName)
                }
            },
            supportingContent = {
                Column {
                    Text(text = "Выдано: ${item.issuedAmount}₽")
                    Text(text = "Долг: ${item.amountDept}₽")
                }
            },
            trailingContent = {
                when (item.status) {
                    LoanStatus.OPEN -> {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .size(24.dp)
                                .background(MaterialTheme.colorScheme.error)
                        )
                    }

                    LoanStatus.CLOSED -> {

                    }
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
            LoanListItem(
                item = LoanShort(
                    "1",
                    "First Name",
                    "Last Name",
                    1,
                    1,
                    LoanStatus.OPEN
                ),
                onClick = {}
            )
        }
    }
}