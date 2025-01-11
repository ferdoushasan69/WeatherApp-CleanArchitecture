package com.example.weatherapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Tabs(
    val title : String,
    val icon : ImageVector
) {
    Home(title = "Home", Icons.Default.Home),
    Daily(icon = Icons.Default.DateRange, title = "Daily")
}