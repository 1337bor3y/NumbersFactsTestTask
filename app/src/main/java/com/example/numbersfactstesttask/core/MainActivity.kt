package com.example.numbersfactstesttask.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.numbersfactstesttask.presentation.get_fact.GetFactScreen
import com.example.numbersfactstesttask.core.ui.theme.NumbersFactsTestTaskTheme
import com.example.numbersfactstesttask.presentation.get_fact.GetFactViewModel
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
                GetFactScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}
