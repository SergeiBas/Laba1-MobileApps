package com.example.laba1.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun ProfileScreen(viewModel: AppViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Text(
                text = viewModel.profileStatus,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.onProfileButtonClick() }) {
                Text("Оновити профіль")
            }
        }
    }
}