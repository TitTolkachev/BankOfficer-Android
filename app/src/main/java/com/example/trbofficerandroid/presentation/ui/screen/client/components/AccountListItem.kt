package com.example.trbofficerandroid.presentation.ui.screen.client.components

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
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountShort
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountState.OPEN
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountType.DEPOSIT
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountType.LOAN

@Composable
fun AccountListItem(item: AccountShort, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .clickable(onClick = onClick)
    ) {
        ListItem(
            headlineContent = {
                Column {
                    Text(text = item.number)
                    Text(text = "${item.balance}₽")
                }
            },
            supportingContent = {
                if (item.type == LOAN)
                    Text(text = "(Кредитный)")
            },
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AccountListItem(
                AccountShort(
                    id = "",
                    number = "12321321313",
                    type = DEPOSIT,
                    balance = 11,
                    state = OPEN
                )
            ) {}
        }
    }
}