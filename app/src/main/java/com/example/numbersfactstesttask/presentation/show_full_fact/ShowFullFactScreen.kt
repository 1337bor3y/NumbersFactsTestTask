package com.example.numbersfactstesttask.presentation.show_full_fact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.numbersfactstesttask.core.util.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowFullFactScreen(
    navController: NavController,
    number: String,
    factText: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Get back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = padding.calculateTopPadding(),
                    horizontal = padding.calculateStartPadding(LayoutDirection.Ltr) + 10.dp
                )
        ) {
            Text(
                modifier = Modifier.testTag(TestTags.FACT_NUMBER),
                text = number,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.testTag(TestTags.FACT_TEXT),
                text = factText,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}