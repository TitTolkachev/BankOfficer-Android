package com.example.trbofficerandroid.presentation.ui.screen.client

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Account
import com.example.trbofficerandroid.domain.model.AccountType.DEPOSIT
import com.example.trbofficerandroid.domain.model.Client
import com.example.trbofficerandroid.domain.model.CreditRating
import com.example.trbofficerandroid.domain.model.Sex
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import com.example.trbofficerandroid.presentation.ui.screen.client.components.AccountListItem
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ClientScreen(
    onBackClick: () -> Unit,
    navigateToAccount: (String) -> Unit,
    navigateToLoan: (String) -> Unit,
    showOfficer: (String) -> Unit,
) {
    val viewModel: ClientViewModel = koinViewModel()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    ClientScreenContent(
        user = viewModel.client.collectAsState().value,
        accounts = viewModel.accounts.collectAsState().value,
        rating = viewModel.rating.collectAsState().value,
        loading = viewModel.loading.collectAsState().value,
        loadingRating = viewModel.loadingRating.collectAsState().value,
        shackBarHostState = shackBarHostState,

        onBackClick = onBackClick,
        onAccountClick = navigateToAccount,
        onLoanClick = navigateToLoan,
        showOfficer = showOfficer,
        updateUserRating = remember { { viewModel.updateUserRating() } },
        blockUser = remember { { viewModel.blockUser() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClientScreenContent(
    user: Client? = null,
    accounts: List<Account>? = listOf(),
    rating: CreditRating? = null,
    loading: Boolean = false,
    loadingRating: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },

    onBackClick: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
    onLoanClick: (String) -> Unit = {},
    showOfficer: (String) -> Unit = {},
    updateUserRating: () -> Unit = {},
    blockUser: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Клиент") },
                navigationIcon = { BackButton(onBackClick) },
                actions = {
                    AnimatedVisibility(visible = loading, enter = scaleIn(), exit = scaleOut()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp,
                            strokeCap = StrokeCap.Round
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = { SnackbarError(it) })
        }
    ) { paddingValues ->
        if (user == null || accounts == null) {
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
                    // Person
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Информация"
                    )
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Фамилия") },
                        value = user.lastName,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Имя") },
                        value = user.firstName,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    if (!user.patronymic.isNullOrBlank()) {
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Отчество") },
                            value = user.patronymic,
                            onValueChange = {},
                            readOnly = true,
                            singleLine = true
                        )
                    }

                    // Data
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Телефон") },
                        value = user.phoneNumber,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Адрес") },
                        value = user.address,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )

                    // Birth Date
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = SimpleDateFormat(
                            "d MMMM yyyy",
                            Locale.getDefault()
                        ).format(Date(user.birthDate)),
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Дата рождения") },
                        readOnly = true,
                        singleLine = true,
                    )

                    // Sex
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = if (user.sex == Sex.MALE) "Мужчина" else "Женщина",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Пол") },
                        readOnly = true,
                        singleLine = true,
                    )

                    // Document
                    Spacer(Modifier.height(16.dp))
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Паспорт"
                    )
                    Spacer(Modifier.height(4.dp))
                    Row {
                        if (!user.passportSeries.isNullOrBlank()) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                label = { Text(text = "Серия") },
                                value = user.passportSeries,
                                onValueChange = {},
                                readOnly = true,
                                singleLine = true
                            )
                            Spacer(Modifier.width(16.dp))
                        }
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            label = { Text(text = "Номер") },
                            value = user.passportNumber,
                            onValueChange = {},
                            readOnly = true,
                            singleLine = true
                        )
                    }

                    // Email and Password
                    Spacer(Modifier.height(16.dp))
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Данные для входа"
                    )
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "E-mail") },
                        value = user.email,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                    )

                    // Credit Rating
                    Spacer(Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            text = "Кредитный рейтинг"
                        )
                        if (loadingRating) {
                            Spacer(Modifier.width(16.dp))
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 3.dp
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            label = { Text(text = "Рейтинг") },
                            value = rating?.rating?.toString() ?: "...",
                            onValueChange = {},
                            readOnly = true,
                            singleLine = true,
                        )
                        IconButton(
                            enabled = !loadingRating,
                            onClick = updateUserRating
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Refresh,
                                contentDescription = null,
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Дата") },
                        value = rating?.calculationDate?.let {
                            SimpleDateFormat(
                                "d MMMM yyyy, HH:mm:ss", Locale.getDefault()
                            ).format(Date(it))
                        } ?: "...",
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                    )

                    if (accounts.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            text = "Счета"
                        )
                        Spacer(Modifier.height(4.dp))
                        accounts.forEach { account ->
                            AccountListItem(
                                item = account,
                                onClick = {
                                    if (account.type == DEPOSIT) onAccountClick(account.id)
                                    else onLoanClick(account.id)
                                }
                            )
                        }
                    }

                    if (!user.whoCreated.isNullOrBlank()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            text = "Добавлен в систему"
                        )
                        Spacer(Modifier.height(16.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { showOfficer(user.whoCreated) }) {
                            Text(text = "Показать сотрудника")
                        }
                    }

                    if (user.blocked) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.error,
                            text = "Заблокирован в системе"
                        )
                        if (!user.whoBlocked.isNullOrBlank()) {
                            Spacer(Modifier.height(16.dp))
                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { showOfficer(user.whoBlocked) }) {
                                Text(text = "Показать сотрудника")
                            }
                        }
                    } else {
                        Spacer(Modifier.height(8.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = blockUser,
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.error,
                            )
                        ) {
                            Text(text = "Заблокировать в системе")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        ClientScreenContent()
    }
}