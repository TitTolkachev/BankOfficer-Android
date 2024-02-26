package com.example.trbofficerandroid.presentation.ui.screen.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.client.components.AccountListItem
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountShort
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountState
import com.example.trbofficerandroid.presentation.ui.screen.client.model.AccountType
import com.example.trbofficerandroid.presentation.ui.screen.client.model.Client

@Composable
fun ClientScreen(
    onBackClick: () -> Unit,
    navigateToAccount: (String) -> Unit
) {

    val client = Client(
        id = "1",
        firstName = "First Name",
        lastName = "Last Name",
        patronymicName = "Patr. Name",
        phoneNumber = "8800-555-35-35",
        address = "SDFsdfsDfsdFSD",
        passportNumber = "sdfsdf",
        passportSeries = "sdfsdf",
        isBlocked = false,
        whoBlocked = "",
        whoCreated = ""
    )

    val accounts = listOf(
        AccountShort(
            id = "1",
            number = "12321321313",
            type = AccountType.DEPOSIT,
            balance = 11,
            state = AccountState.OPEN
        ),
        AccountShort(
            id = "2",
            number = "22222222222",
            type = AccountType.DEPOSIT,
            balance = 120301,
            state = AccountState.OPEN
        ),
        AccountShort(
            id = "3",
            number = "12312311111",
            type = AccountType.DEPOSIT,
            balance = 0,
            state = AccountState.CLOSED
        ),
        AccountShort(
            id = "3",
            number = "66666666666",
            type = AccountType.LOAN,
            balance = 999999999,
            state = AccountState.OPEN
        ),
    )

    ClientScreenContent(
        client = client,
        accounts = accounts,

        onBackClick = onBackClick,
        onAccountClick = navigateToAccount
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClientScreenContent(
    client: Client,
    accounts: List<AccountShort>,

    onBackClick: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Клиент") }
            )
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Text(
                text = client.firstName
            )
            Text(
                text = client.lastName
            )
            client.patronymicName?.let {
                Text(
                    text = client.patronymicName
                )
            }

            Text(text = client.phoneNumber)
            Text(text = client.address)
            Text(text = client.passportNumber)
            Text(text = client.passportSeries)

            Text(text = "Счета:")
            accounts.forEach {
                AccountListItem(
                    item = it,
                    onClick = { onAccountClick(it.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        ClientScreenContent(
            client = Client(
                id = "1",
                firstName = "First Name",
                lastName = "Last Name",
                patronymicName = "Patr. Name",
                phoneNumber = "8800-555-35-35",
                address = "SDFsdfsDfsdFSD",
                passportNumber = "sdfsdf",
                passportSeries = "sdfsdf",
                isBlocked = false,
                whoBlocked = "",
                whoCreated = ""
            ),
            accounts = listOf(
                AccountShort(
                    id = "1",
                    number = "12321321313",
                    type = AccountType.DEPOSIT,
                    balance = 11,
                    state = AccountState.OPEN
                ),
                AccountShort(
                    id = "2",
                    number = "22222222222",
                    type = AccountType.DEPOSIT,
                    balance = 120301,
                    state = AccountState.OPEN
                ),
                AccountShort(
                    id = "3",
                    number = "12312311111",
                    type = AccountType.DEPOSIT,
                    balance = 0,
                    state = AccountState.CLOSED
                ),
                AccountShort(
                    id = "3",
                    number = "66666666666",
                    type = AccountType.LOAN,
                    balance = 999999999,
                    state = AccountState.OPEN
                ),
            )
        )
    }
}