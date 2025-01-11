package com.example.weatherapp.presentation.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.presentation.home.SunSetWeatherItem
import com.example.weatherapp.presentation.home.UvIndexWeatherItem

@Composable
fun DailyScreen(
    modifier: Modifier = Modifier,
    dailyVIewModel: DailyViewModel = hiltViewModel(),
) {

    val dailyState = dailyVIewModel.dailyState
    var selectedWeatherIndex by remember { mutableIntStateOf(0) }
    val currentDailyWeatherItem = remember(key1 = selectedWeatherIndex, key2 = dailyState) {
        dailyState.daily?.weatherInfo[selectedWeatherIndex]
    }
    Column(
        modifier = modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        when (dailyState.isLoading) {
            true -> {
                CircularProgressIndicator()
            }

            else -> {
                currentDailyWeatherItem?.let {
                    Text(
                        text = "Max :${it.temperature_2m_max} Min : ${it.temperature_2m_min}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "7 Days Forecast",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(8.dp))
                    LazyRow {
                        dailyState.daily?.let { daily ->
                            itemsIndexed(daily.weatherInfo) { index, item ->
                                val selectedColor =
                                    if (selectedWeatherIndex == index) MaterialTheme.colorScheme.inverseSurface
                                    else CardDefaults.cardColors().containerColor
                                DailyWeatherInfoItem(
                                    modifier = modifier,
                                    daily = item,
                                    bgColor = selectedColor,
                                    onClick = { selectedWeatherIndex = index }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    currentDailyWeatherItem?.let {
                        Card (elevation = CardDefaults.elevatedCardElevation(2.dp)){
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(painter = painterResource(id = R.drawable.wind_ic),contentDescription = null)
                                    Spacer(Modifier.width(8.dp))
                                    Text(text = "WIND", style = MaterialTheme.typography.bodyMedium)
                                    Spacer(Modifier.width(4.dp))


                                }
                                Text(
                                    text = "${it.wind_speed_10m_max} km/h ${it.wind_direction_10m_dominant}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                            Spacer(Modifier.height(8.dp))
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                SunSetWeatherItem(
                                    modifier = Modifier,
                                    daily = it
                                )
                                UvIndexWeatherItem(
                                    modifier = Modifier,
                                    hourly = it
                                )
                            }
                    }
                }
            }
        }
    }

}

@Composable
fun DailyWeatherInfoItem(
    modifier: Modifier,
    daily: Daily.WeatherInfo,
    bgColor: Color,
    onClick: () -> Unit,
) {
    Card(
        onClick = { onClick() },
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${daily.temperature_2m_max}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(8.dp))
            Icon(painter = painterResource(id = daily.weather_code.icon), contentDescription = null)
            Spacer(Modifier.height(8.dp))

            Text(
                text = daily.time,
                style = MaterialTheme.typography.bodySmall

            )
        }

    }
}