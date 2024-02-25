package com.example.trbofficerandroid.presentation.ui.screen.ratelist

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.EmptyList
import com.example.trbofficerandroid.presentation.ui.screen.ratelist.components.RateListItem
import com.example.trbofficerandroid.presentation.ui.screen.ratelist.model.RateShort
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun RateListScreen(
    fabActions: SharedFlow<Unit>,
    onRateClick: (String) -> Unit,
    onAddRateClick: () -> Unit,
) {
    val viewModel: RateListViewModel = koinViewModel()
    val items by viewModel.rateList.collectAsState()

    // Fab Actions Handling
    LaunchedEffect(Unit) {
        fabActions.collect {
            onAddRateClick()
        }
    }

    RateListScreenContent(
        items = items,
        onRateClick = onRateClick
    )
}

@Composable
private fun RateListScreenContent(
    items: List<RateShort>?,
    onRateClick: (String) -> Unit = {}
) {
    if (items == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (items.isEmpty()) {
        EmptyList()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = spacedBy(8.dp),
            horizontalArrangement = spacedBy(8.dp),
        ) {
            items(items = items, key = { it.id }) {
                RateListItem(item = it, onClick = { onRateClick(it.id) })
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            RateListScreenContent(
                items = listOf(
                    RateShort(
                        id = "1",
                        name = "Обычный тариф",
                        percentageRate = 15.5
                    ),
                    RateShort(
                        id = "2",
                        name = "Лучший февральский тариф",
                        percentageRate = 16.5
                    ),
                    RateShort(
                        id = "3",
                        name = "Лучший мартовский тариф",
                        percentageRate = 10.5
                    ),
                    RateShort(
                        id = "4",
                        name = "Лучший апрельский тариф",
                        percentageRate = 11.5
                    ),
                    RateShort(
                        id = "5",
                        name = "Семейный",
                        percentageRate = 1.5
                    ),
                )
            )
        }
    }
}