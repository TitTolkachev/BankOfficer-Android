package com.example.hbofficerandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hbofficerandroid.presentation.navigation.RootNavGraph
import com.example.hbofficerandroid.presentation.navigation.Screen
import com.example.hbofficerandroid.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RootNavGraph(
                    navController = rememberNavController(),
                    startDestination = Screen.Home.route,
                    modifier = Modifier,
                )
            }
        }
    }
}