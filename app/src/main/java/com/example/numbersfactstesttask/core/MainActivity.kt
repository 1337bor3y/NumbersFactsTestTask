package com.example.numbersfactstesttask.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.numbersfactstesttask.presentation.get_fact.GetFactScreen
import com.example.numbersfactstesttask.core.ui.theme.NumbersFactsTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumbersFactsTestTaskTheme {
                GetFactScreen()
            }
        }
    }
}
