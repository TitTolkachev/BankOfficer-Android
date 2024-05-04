package com.example.trbofficerandroid.presentation.ui.screen.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel

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
    transactions: List<String> = listOf(),
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
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
                account?.let {
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
                }

                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "Транзакции"
                )
                Spacer(Modifier.height(4.dp))
                transactions.forEach { transaction ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = transaction,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        supportingContent = {
                            Text(
                                text = "<test>",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AccountScreenContent(
                transactions = listOf(
                    "transaction 1",
                    "transaction 2",
                )
            )
        }
    }
}