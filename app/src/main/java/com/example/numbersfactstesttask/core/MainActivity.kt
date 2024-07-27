package com.example.numbersfactstesttask.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.numbersfactstesttask.presentation.get_fact.GetFactScreen
import com.example.numbersfactstesttask.core.ui.theme.NumbersFactsTestTaskTheme
import com.example.numbersfactstesttask.presentation.get_fact.GetFactViewModel
import com.example.numbersfactstesttask.presentation.show_full_fact.ShowFullFactScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumbersFactsTestTaskTheme {
                val viewModel = hiltViewModel<GetFactViewModel>()
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MainScreenRoutes.GetFactScreen
                ) {
                    composable<MainScreenRoutes.GetFactScreen> {
                        GetFactScreen(
                            navController = navController,
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<MainScreenRoutes.ShowFullFactScreen> {
                        val numberFact = it.toRoute<MainScreenRoutes.ShowFullFactScreen>()
                        ShowFullFactScreen(
                            navController = navController,
                            number = numberFact.number,
                            factText = numberFact.factText
                        )
                    }
                }
            }
        }
    }
}
