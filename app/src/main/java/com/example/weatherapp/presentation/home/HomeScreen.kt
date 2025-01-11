package com.example.weatherapp.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.Daily
import com.example.weatherapp.domain.model.Hourly
import com.example.weatherapp.utils.Util
import java.util.Date

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeVIewModel: HomeVIewModel = hiltViewModel(),
) {

    var homeState = homeVIewModel.homeState
    Log.d(TAG, "daily weather info: ${homeState.dailyWeatherInfo}")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        when (homeState.isLoading) {
            true -> {
                CircularProgressIndicator()
            }

            else -> {

                homeState.data?.let {
                    CurrentWeatherItem(
                        modifier = Modifier,
                        it.currentWeather
                    )


                    homeState.dailyWeatherInfo?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
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
                    HourlyWeatherItem(hourly = it.hourly, modifier = Modifier)

                }


            }
        }
    }

}

@Composable
fun UvIndexWeatherItem(modifier: Modifier, hourly: Daily.WeatherInfo) {
    Card(modifier = modifier.padding(horizontal = 8.dp), elevation = CardDefaults.elevatedCardElevation(2.dp)) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Uv Index",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "${hourly.uv_index_max}",
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "Status : ${hourly.weather_code.info}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SunSetWeatherItem(
    modifier: Modifier,
    daily: Daily.WeatherInfo,
) {
    Card(modifier = modifier.padding(horizontal = 8.dp), elevation = CardDefaults.elevatedCardElevation(2.dp)) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SunRise",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = daily.sunrise,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "Sunset : ${daily.sunset}"
            )
        }
    }
}

@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourly: Hourly,
) {
    Card(modifier = modifier.fillMaxWidth(), elevation = CardDefaults.elevatedCardElevation(2.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Toady", style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = Util.formatNormalDate("MMMM,dd", Date().time),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyRow(modifier = Modifier.padding(16.dp)) {
            items(hourly.weatherInfo) {
                HourlyWeatherInfoItem(item = it, modifier = Modifier)
            }
        }
    }
}

@Composable
fun HourlyWeatherInfoItem(
    modifier: Modifier,
    item: Hourly.HourlyInfoItem,
) {
    Column {
        Text(
            text = "${item.temperature_2m}°c ",
            modifier.padding(end = 3.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(item.weatherCode.icon), contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.time
        )
    }
}

@Composable
fun CurrentWeatherItem(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = currentWeather.weatherCode.icon),
            contentDescription = null,
            modifier = Modifier.size(130.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${currentWeather.temperature2m} °c",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Weather Status :${currentWeather.weatherCode.info}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Wind Speed ${currentWeather.windSpeed10m} km/h ${currentWeather.windDirection10m}",
            style = MaterialTheme.typography.bodyMedium

        )


    }

}