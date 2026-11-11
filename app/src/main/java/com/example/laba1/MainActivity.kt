package com.example.laba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laba1.ui.theme.Laba1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laba1Theme {
                Laba1App()
            }
        }
    }
}

// 5 ЗАВДАННЯ: Enum з власними елементами навігації
enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    PROFILE("Profile", R.drawable.ic_account_box),
    SETTINGS("Settings", android.R.drawable.ic_menu_preferences), // Використано системну іконку налаштувань
}

@PreviewScreenSizes
@Composable
fun Laba1App() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            // Відображаємо потрібний екран залежно від поточного вибору в меню
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentDestination) {
                    AppDestinations.HOME -> HomeScreen()
                    AppDestinations.PROFILE -> ProfileScreen()
                    AppDestinations.SETTINGS -> SettingsScreen()
                }
            }
        }
    }
}

// 1. Екран HOME (Центральне вирівнювання + rememberSaveable)
@Composable
fun HomeScreen() {
    var clickCount by rememberSaveable { mutableStateOf(0) }
    var message by rememberSaveable { mutableStateOf("Головний екран (Home)") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Натискань: $clickCount",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = {
            clickCount++
            message = "Текст змінено на Home!"
        }) {
            Text("Кнопка Home")
        }
    }
}

// 2. Екран PROFILE (Вирівнювання внизу через Box + rememberSaveable)
@Composable
fun ProfileScreen() {
    var profileStatus by rememberSaveable { mutableStateOf("Профіль користувача: Неактивний") }

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
                text = profileStatus,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                profileStatus = "Статус: Профіль оновлено!"
            }) {
                Text("Оновити профіль")
            }
        }
    }
}

// 3. Екран SETTINGS (Вирівнювання ліворуч зверху + rememberSaveable)
@Composable
fun SettingsScreen() {
    var themeText by rememberSaveable { mutableStateOf("Поточні налаштування: Стандартні") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = themeText,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Button(
            onClick = {
                themeText = "Налаштування застосовано!"
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Зберегти параметри")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Laba1Theme {
        Laba1App()
    }
}