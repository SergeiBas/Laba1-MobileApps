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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.laba1.ui.theme.Laba1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laba1Theme {
                MainAppNavigation()
            }
        }
    }
}

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                AppDestinations.entries.forEach { destination ->
                    NavigationBarItem(
                        icon = { Icon(painterResource(destination.icon), contentDescription = destination.label) },
                        label = { Text(destination.label) },
                        selected = currentRoute == destination.route,
                        onClick = {
                            if (currentRoute != destination.route) {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestinations.HOME.route) {
                HomeScreen()
            }
            composable(AppDestinations.PROFILE.route) {
                ProfileScreen()
            }
            composable(AppDestinations.SETTINGS.route) {
                SettingsScreen()
            }
        }
    }
}

// 1. Екран HOME
@Composable
fun HomeScreen() {
    // Зберігаємо стан навіть при повороті екрана
    var clickCount by rememberSaveable { mutableStateOf(0) }
    var displayMessage by rememberSaveable { mutableStateOf("Головний екран (Home)") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Мітка, яка змінює свій вміст при натисканні
        Text(
            text = displayMessage,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Кількість натискань: $clickCount",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = {
            clickCount++
            displayMessage = "Кнопку натиснуто разів: $clickCount"
        }) {
            Text("Змінити текст мітки")
        }
    }
}

// 2. Екран PROFILE
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            Text(
                text = "Профіль користувача",
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { /* Дія для кнопки Profile */ }) {
                Text("Редагувати профіль")
            }
        }
    }
}

// 3. Екран SETTINGS
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Налаштування додатку",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = { /* Дія для кнопки Settings */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Змінити параметри")
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
    val route: String
) {
    HOME("Home", R.drawable.ic_home, route = "home_screen"),
    PROFILE("Profile", R.drawable.ic_account_box, route = "profile_screen"),
    SETTINGS("Settings", android.R.drawable.ic_menu_preferences, route = "settings_screen"),
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Laba1Theme {
        MainAppNavigation()
    }
}