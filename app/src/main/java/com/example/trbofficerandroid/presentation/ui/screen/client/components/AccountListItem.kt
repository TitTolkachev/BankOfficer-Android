package com.example.trbofficerandroid.presentation.ui.screen.client.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.AccountType

@Composable
fun AccountListItem(item: Account, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .clickable(onClick = onClick)
    ) {
        ListItem(
            headlineContent = {
                Column {
                    Text(
                        text = item.id,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "${item.balance} ${item.currency}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            supportingContent = {
                if (item.type == AccountType.LOAN)
                    Text(
                        text = "Кредитный",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
            },
        )
        HorizontalDivider()
    }
}