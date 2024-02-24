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
import org.koin.androidx.compose.koinViewModel

@Composable
fun RateListScreen() {
    val viewModel: RateListViewModel = koinViewModel()
    val items by viewModel.rateList.collectAsState()

    RateListScreenContent(
        items = items,
    )
}

@Composable
private fun RateListScreenContent(
    items: List<RateShort>?
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
                RateListItem(Modifier, it)
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
                        limit = "1000₽",
                        percentageRate = 15.5,
                        period = "5 лет"
                    ),
                    RateShort(
                        id = "2",
                        name = "Лучший февральский тариф",
                        limit = "100₽",
                        percentageRate = 16.5,
                        period = "3 месяца"
                    ),
                    RateShort(
                        id = "3",
                        name = "Лучший мартовский тариф",
                        limit = "10₽",
                        percentageRate = 10.5,
                        period = "1 месяц"
                    ),
                    RateShort(
                        id = "4",
                        name = "Лучший апрельский тариф",
                        limit = "1010₽",
                        percentageRate = 11.5,
                        period = "6 лет"
                    ),
                )
            )
        }
    }
}