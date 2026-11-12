package com.example.laba1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    // Стан для HomeScreen
    var homeClickCount by mutableIntStateOf(0)
        private set
    var homeMessage by mutableStateOf("Головний екран (Home)")
        private set

    fun onHomeButtonClick() {
        homeClickCount++
        homeMessage = "Текст змінено через ViewModel!"
    }

    // Стан для ProfileScreen
    var profileStatus by mutableStateOf("Профіль користувача: Неактивний")
        private set

    fun onProfileButtonClick() {
        profileStatus = "Статус: Профіль оновлено!"
    }

    // Стан для SettingsScreen
    var settingsText by mutableStateOf("Поточні налаштування: Стандартні")
        private set

    fun onSettingsButtonClick() {
        settingsText = "Налаштування застосовано!"
    }
}