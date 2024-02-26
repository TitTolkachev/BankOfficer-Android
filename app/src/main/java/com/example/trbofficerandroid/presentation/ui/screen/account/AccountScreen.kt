package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.account.components.TransactionListItem
import com.example.trbofficerandroid.presentation.ui.screen.account.model.Account
import com.example.trbofficerandroid.presentation.ui.screen.account.model.TransactionShort
import com.example.trbofficerandroid.presentation.ui.screen.account.model.TransactionType.WITHDRAWAL
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountState
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountType

@Composable
fun AccountScreen(onBackClick: () -> Unit) {

    val account = Account(
        id = "",
        number = "12321321313",
        type = AccountType.DEPOSIT,
        balance = 11,
        state = AccountState.OPEN
    )

    val items = listOf(
        TransactionShort(
            id = "14112311122",
            type = WITHDRAWAL,
        ),
        TransactionShort(
            id = "11111111111",
            type = WITHDRAWAL,
        ),
        TransactionShort(
            id = "22222222222",
            type = WITHDRAWAL,
        ),
        TransactionShort(
            id = "33333344444",
            type = WITHDRAWAL,
        ),
    )

    AccountScreenContent(
        account = account,
        items = items,

        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountScreenContent(
    account: Account,
    items: List<TransactionShort>,

    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Детали счета") })
        }
    ) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            item {
                Text(text = "Баланс: ${account.balance}₽")
            }
            items(items = items, key = { it.id }) {
                TransactionListItem(item = it, onClick = {})
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AccountScreenContent(
                account = Account(
                    id = "",
                    number = "12321321313",
                    type = AccountType.DEPOSIT,
                    balance = 11,
                    state = AccountState.OPEN
                ),
                items = listOf(
                    TransactionShort(
                        id = "14112311122",
                        type = WITHDRAWAL,
                    ),
                    TransactionShort(
                        id = "11111111111",
                        type = WITHDRAWAL,
                    ),
                    TransactionShort(
                        id = "22222222222",
                        type = WITHDRAWAL,
                    ),
                    TransactionShort(
                        id = "33333344444",
                        type = WITHDRAWAL,
                    ),
                )
            )
        }
    }
}