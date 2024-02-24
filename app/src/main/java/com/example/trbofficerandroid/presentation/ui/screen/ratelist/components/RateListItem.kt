package com.example.trbofficerandroid.presentation.ui.screen.ratelist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.screen.ratelist.model.RateShort

@Composable
fun RateListItem(modifier: Modifier = Modifier, item: RateShort, onClick: () -> Unit = {}) {
    OutlinedCard(modifier = modifier, onClick = onClick) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Сумма",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = item.limit,
                style = MaterialTheme.typography.titleMedium
            )
            Surface(
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(100),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),
                    text = "${item.percentageRate}%"
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            RateListItem(
                item = RateShort(
                    id = "",
                    name = "Лучший февральский тариф",
                    limit = "100₽",
                    percentageRate = 12.5,
                    period = "3 месяца"
                )
            )
        }
    }
}