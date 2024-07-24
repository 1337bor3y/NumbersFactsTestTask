package com.example.numbersfactstesttask.presentation.show_full_fact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowFullFactScreen(
    number: String,
    factText: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 50.dp,
                horizontal = 20.dp
            ),
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = factText,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}