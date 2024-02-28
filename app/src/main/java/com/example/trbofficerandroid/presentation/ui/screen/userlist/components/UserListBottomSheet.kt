package com.example.trbofficerandroid.presentation.ui.screen.userlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListBottomSheet(
    showBottomSheet: Boolean = true,
    hideBottomSheet: () -> Unit = {},
    onAddClientClick: () -> Unit = {},
    onAddOfficerClick: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = hideBottomSheet
        ) {
            UserListBottomSheetContent(
                onAddClientClick = remember {
                    {
                        onAddClientClick()
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) hideBottomSheet()
                        }
                    }
                },
                onAddOfficerClick = remember {
                    {
                        onAddOfficerClick()
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) hideBottomSheet()
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun UserListBottomSheetContent(
    onAddClientClick: () -> Unit = {},
    onAddOfficerClick: () -> Unit = {},
) {
    Column(Modifier.padding(16.dp)) {
        Card(onClick = onAddClientClick) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Добавить клиента")
                Icon(
                    painter = painterResource(id = R.drawable.self_improvement_24),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(onClick = onAddOfficerClick) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Добавить сотрудника")
                Icon(
                    painter = painterResource(id = R.drawable.groups_filled_24),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            val sheetState = SheetState(
                skipPartiallyExpanded = false,
                density = LocalDensity.current,
                initialValue = SheetValue.Expanded
            )
            ModalBottomSheet(sheetState = sheetState, onDismissRequest = { }) {
                UserListBottomSheetContent()
            }
        }
    }
}