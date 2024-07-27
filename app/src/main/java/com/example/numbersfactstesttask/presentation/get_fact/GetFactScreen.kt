package com.example.numbersfactstesttask.presentation.get_fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.numbersfactstesttask.core.MainScreenRoutes
import com.example.numbersfactstesttask.core.util.TestTags
import com.example.numbersfactstesttask.presentation.get_fact.components.FactListItem

@Composable
fun GetFactScreen(
    navController: NavController,
    state: GetFactScreenState,
    onEvent: (GetFactScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp),
            value = state.number,
            onValueChange = {
                onEvent(
                    GetFactScreenEvent.SetNumber(
                        it
                    )
                )
            },
            label = {
                Text(text = "Enter your number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp),
            onClick = {
                if (state.number.isNotBlank()) {
                    onEvent(
                        GetFactScreenEvent.GetFact(
                            state.number
                        )
                    )
                }
            }
        ) {
            Text(text = "Get fact")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp),
            onClick = {
                onEvent(
                    GetFactScreenEvent.GetRandomFact
                )
            }
        ) {
            Text(text = "Get fact about random number")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .testTag(TestTags.ERROR_MESSAGE)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .testTag(TestTags.FACT_LAZY_COLUMN)
            ) {
                items(state.facts.reversed()) { fact ->
                    FactListItem(
                        fact = fact,
                        onItemClick = {
                            navController.navigate(
                                MainScreenRoutes.ShowFullFactScreen(
                                    number = fact.number,
                                    factText = fact.fact
                                )
                            )
                        }
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag(TestTags.LOADING)
                )
            }
        }
    }
}
