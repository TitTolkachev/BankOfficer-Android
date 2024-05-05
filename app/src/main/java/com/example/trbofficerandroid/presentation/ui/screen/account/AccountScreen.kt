package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.AccountType
import com.example.trbofficerandroid.domain.model.Transaction
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AccountScreen(navigateBack: () -> Unit) {
    val viewModel: AccountViewModel = koinViewModel()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    AccountScreenContent(
        account = viewModel.account.collectAsState().value,
        transactions = viewModel.transactions.collectAsState().value,
        shackBarHostState = shackBarHostState,
        onBackClick = navigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountScreenContent(
    account: Account? = null,
    transactions: List<Transaction>? = listOf(),
    shackBarHostState: SnackbarHostState = SnackbarHostState(),
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Счет клиента") },
                navigationIcon = { BackButton(onBackClick) }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = { SnackbarError(it) })
        }
    ) { paddingValues ->
        if (account == null || transactions == null) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Информация"
                    )
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Id") },
                        value = account.id,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Валюта") },
                        value = account.currency,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Тип") },
                        value = account.type.name,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    Spacer(Modifier.height(16.dp))

                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Транзакции"
                    )
                    Spacer(Modifier.height(4.dp))
                    if (transactions.isEmpty()) {
                        Text(
                            text = "Список пуст",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                    } else {
                        transactions.forEach { transaction ->
                            ListItem(
                                headlineContent = {
                                    Column {
                                        Text(
                                            text = transaction.id,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        transaction.payerAccountId?.let {
                                            Spacer(Modifier.height(2.dp))
                                            Text(
                                                text = "FROM: $it",
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                            Spacer(Modifier.height(2.dp))
                                        }
                                        transaction.payeeAccountId?.let {
                                            Spacer(Modifier.height(2.dp))
                                            Text(
                                                text = "TO: $it",
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                            Spacer(Modifier.height(2.dp))
                                        }
                                    }
                                },
                                supportingContent = {
                                    Column {
                                        Text(
                                            text = transaction.type,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                        Text(
                                            text = SimpleDateFormat(
                                                "d MMMM yyyy, HH:mm:ss", Locale.getDefault()
                                            ).format(Date(transaction.date)),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                },
                                trailingContent = {
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text(
                                            text = transaction.amount.toString(),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Text(
                                            text = transaction.currency,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
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
                    id = "123-123",
                    type = AccountType.DEPOSIT,
                    balance = 123.12,
                    currency = "RUB",
                    clientFullName = "Full Name",
                    externalClientId = "123-123",
                    creationDate = 12312312312,
                    closingDate = 12312312312,
                    isClosed = false,
                ),
                transactions = listOf()
            )
        }
    }
}