package com.example.trbofficerandroid.presentation.ui.screen.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trbofficerandroid.databinding.YandexFeedBinding
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.feed.FeedAd
import com.yandex.mobile.ads.feed.FeedAdAdapter
import com.yandex.mobile.ads.feed.FeedAdAppearance
import com.yandex.mobile.ads.feed.FeedAdEventListener
import com.yandex.mobile.ads.feed.FeedAdLoadListener
import com.yandex.mobile.ads.feed.FeedAdRequestConfiguration

@Composable
fun MainScreen() {
    var isLoading by remember {
        mutableStateOf(true)
    }
    MainScreenContent(
        isLoading = isLoading,
        onAdLoaded = remember { { isLoading = false } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreenContent(
    isLoading: Boolean = true,
    onAdLoaded: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Trust Bank") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            Feed(onAdLoaded = onAdLoaded)
        }
    }
}

@Composable
fun Feed(
    onAdLoaded: () -> Unit,
) {
    AndroidView(
        factory = { context ->
            val feedMarginDp = 24
            val screenWidthDp = context.resources.configuration.screenWidthDp
            val cardWidthDp = screenWidthDp - 2 * feedMarginDp
            val cardCornerRadiusDp = 16.0

            // Appearance
            val feedAdAppearance = FeedAdAppearance.Builder(cardWidthDp)
                .setCardCornerRadius(cardCornerRadiusDp)
                .build()

            // Configuration
//            val adUnitId = "R-M-7232927-1"
            val adUnitId = "demo-feed-yandex"
            val feedAdRequestConfiguration = FeedAdRequestConfiguration.Builder(adUnitId).build()

            // LoadListener
            val feedAdLoadListener = object : FeedAdLoadListener {
                override fun onAdLoaded() {
                    onAdLoaded()
                }

                override fun onAdFailedToLoad(error: AdRequestError) {
                }
            }

            // Init
            val feedAd =
                FeedAd.Builder(context, feedAdRequestConfiguration, feedAdAppearance).build()
            feedAd.loadListener = feedAdLoadListener

            // Preload
            feedAd.preloadAd()

            // Event Listener
            val feedAdEventListener = object : FeedAdEventListener {
                override fun onAdClicked() {
                }

                override fun onImpression(impressionData: ImpressionData?) {
                }
            }

            val feedAdAdapter = FeedAdAdapter(feedAd)
            feedAdAdapter.eventListener = feedAdEventListener

            val layoutInflater = context.getSystemService<LayoutInflater>()
            val binding = YandexFeedBinding.inflate(layoutInflater!!)
            binding.feedRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.feedRecyclerView.adapter = feedAdAdapter
            binding.root
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MainScreenContent()
    }
}