package com.example.hbofficerandroid.presentation.ui.screen.userlist.components

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
import com.example.hbofficerandroid.presentation.theme.AppTheme
import com.example.hbofficerandroid.presentation.ui.screen.userlist.model.UserShort

@Composable
fun ClientListItem(item: UserShort, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .clickable(onClick = onClick)
    ) {
        ListItem(
            headlineContent = {
                Column {
                    Text(text = item.lastName)
                    Text(text = item.firstName)
                }
            },
            supportingContent = {
                Text(text = item.birthDate)
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
            ClientListItem(
                UserShort("1", "Карл", "Иванов", "01.01.2020"),
            )
        }
    }
}