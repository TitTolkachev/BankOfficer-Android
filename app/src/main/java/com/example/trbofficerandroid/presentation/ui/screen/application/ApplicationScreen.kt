package com.example.trbofficerandroid.presentation.ui.screen.application

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Application
import com.example.trbofficerandroid.domain.model.ApplicationState
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale.getDefault

@Composable
fun ApplicationScreen(
    navigateBack: () -> Unit,
    navigateToClient: (String?) -> Unit,
    navigateToTariff: (Tariff) -> Unit,
    navigateToOfficer: (String?) -> Unit,
) {
    val viewModel: ApplicationViewModel = koinViewModel()
    val application by viewModel.application.collectAsState()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    ApplicationScreenContent(
        application = application,
        loading = viewModel.loading.collectAsState().value,
        shackBarHostState = shackBarHostState,

        onBackClick = navigateBack,
        onShowClientClick = remember { { application?.clientId?.let { navigateToClient(it) } } },
        onShowTariffClick = remember { { application?.tariff?.let { navigateToTariff(it) } } },
        onShowOfficerClick = remember { { application?.officerId?.let { navigateToOfficer(it) } } },
        onApproveClick = remember { { viewModel.approveApplication() } },
        onRejectClick = remember { { viewModel.rejectApplication() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ApplicationScreenContent(
    application: Application? = null,
    loading: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },

    onBackClick: () -> Unit = {},
    onShowClientClick: () -> Unit = {},
    onShowTariffClick: () -> Unit = {},
    onShowOfficerClick: () -> Unit = {},
    onApproveClick: () -> Unit = {},
    onRejectClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Заявка на кредит") },
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
        if (application == null) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "Id")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = application.id,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Статус")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = when (application.state) {
                        ApplicationState.UNDER_CONSIDERATION -> "Заявка на рассмотрении"
                        ApplicationState.APPROVED -> "Заявка одобрена"
                        ApplicationState.REJECTED -> "Заявка отклонена"
                        ApplicationState.FAILED -> "ОШИБКА"
                    },
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Дата создания заявки")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = SimpleDateFormat("d MMMM yyyy", getDefault()).format(
                        Date(application.creationDate)
                    ),
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Запрошенная сумма")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "${application.issuedAmount / 100}₽",
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Длительность кредита в днях")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = application.loanTermInDays.toString(),
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Процентная ставка")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "${application.tariff.interestRate}%",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onShowClientClick,
                    content = { Text(text = "Показать клиента") }
                )

                Spacer(Modifier.height(16.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onShowTariffClick,
                    content = { Text(text = "Показать тариф") }
                )

                if (application.state == ApplicationState.UNDER_CONSIDERATION) {
                    Spacer(Modifier.height(16.dp))
                    Row {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onRejectClick,
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.error,
                            ),
                            content = { Text(text = "Отклонить") }
                        )
                        Spacer(Modifier.width(16.dp))
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onApproveClick,
                            content = { Text(text = "Одобрить") }
                        )
                    }
                } else {
                    Spacer(Modifier.height(16.dp))
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onShowOfficerClick,
                        content = { Text(text = "Показать сотрудника") }
                    )
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
            ApplicationScreenContent(
                application = null
            )
        }
    }
}