package com.example.trbofficerandroid.presentation.ui.screen.loan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.domain.model.Loan
import com.example.trbofficerandroid.domain.model.LoanRepaymentState
import com.example.trbofficerandroid.domain.model.Tariff
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LoanScreen(
    onTariffClick: (Tariff) -> Unit,
    navigateBack: () -> Unit,
) {
    val viewModel: LoanViewModel = koinViewModel()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    LoanScreenContent(
        loan = viewModel.loan.collectAsState().value,
        shackBarHostState = shackBarHostState,
        onTariffClick = onTariffClick,
        onBackClick = navigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoanScreenContent(
    loan: Loan? = null,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onTariffClick: (Tariff) -> Unit,
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Кредит") },
                navigationIcon = { BackButton(onBackClick) }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = { SnackbarError(it) })
        }
    ) { paddingValues ->
        if (loan == null) {
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
                        value = loan.id,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )

                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Размер кредита") },
                        value = loan.issuedAmount.toString(),
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )

                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Долг") },
                        value = loan.amountDebt.toString(),
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )

                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Выдан") },
                        value = SimpleDateFormat(
                            "d MMMM yyyy",
                            Locale.getDefault()
                        ).format(Date(loan.issuedDate)),
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Тариф"
                    )
                    Spacer(Modifier.height(8.dp))
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        onClick = { onTariffClick(loan.tariff) }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            text = loan.tariff.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    if (loan.repayments.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            text = "Выплаты"
                        )
                        Spacer(Modifier.height(4.dp))
                        loan.repayments.forEach { repayment ->
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = "${repayment.amount}",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                supportingContent = {
                                    Text(
                                        text = SimpleDateFormat(
                                            "d MMMM yyyy", Locale.getDefault()
                                        ).format(Date(repayment.date)),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                trailingContent = {
                                    Text(
                                        text = when (repayment.state) {
                                            LoanRepaymentState.OPEN -> "OPEN"
                                            LoanRepaymentState.IN_PROGRESS -> "IN_PROGRESS"
                                            LoanRepaymentState.DONE -> "DONE"
                                            LoanRepaymentState.REJECTED -> "REJECTED"
                                        },
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            )
                            HorizontalDivider()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}