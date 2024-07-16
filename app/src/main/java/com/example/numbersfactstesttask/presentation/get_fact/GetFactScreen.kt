package com.example.numbersfactstesttask.presentation.get_fact

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numbersfactstesttask.presentation.get_fact.components.FactListItem

@Preview(showBackground = true)
@Composable
fun GetFactScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(70.dp, 0.dp),
            value = "",
            onValueChange = {},
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
                .padding(70.dp, 0.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Get fact")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(70.dp, 0.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Get fact about random number")
        }
        Spacer(modifier = Modifier.height(40.dp))
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(10) {
                FactListItem(text = "Hello") {
                    Log.d("Hello", it)
                }
            }
        }
    }
}
