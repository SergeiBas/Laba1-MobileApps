package com.example.laba1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laba1.viewmodel.AppViewModel

@Composable
fun HomeScreen(viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewModel.homeMessage,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Натискань: ${viewModel.homeClickCount}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { viewModel.onHomeButtonClick() }) {
            Text("Кнопка Home")
        }
    }
}