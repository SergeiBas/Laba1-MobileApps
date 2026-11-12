package com.example.laba1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {

    val homeClickCount : StateFlow<Int>
        field = MutableStateFlow(0)

    val homeMessage : StateFlow<String>
        field = MutableStateFlow("Головний екран (Home)")

    fun onHomeButtonClick() {
        homeClickCount.value++
        homeMessage.value = "Текст змінено через ViewModel!"
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