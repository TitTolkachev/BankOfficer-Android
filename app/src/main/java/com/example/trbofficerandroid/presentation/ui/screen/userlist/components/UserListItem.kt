package com.example.trbofficerandroid.presentation.ui.screen.userlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort

@Composable
fun UserListItem(item: UserShort, onClick: () -> Unit = {}) {
    Column(Modifier.clickable(onClick = onClick)) {
        ListItem(
            modifier = Modifier.padding(horizontal = 16.dp),
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
        HorizontalDivider(Modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            UserListItem(
                UserShort("1", "Карл", "Иванов", "01.01.2020"),
            )
        }
    }
}