package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.presentation.daily.DailyScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.utils.Tabs
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.presentation.home.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherAppScaffold()
            }
        }
    }
}


@Composable
fun WeatherAppScaffold() {
    var selectedTabIndex = rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
       modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier,
                tabs = Tabs.entries,
                selectedIndex = selectedTabIndex.value,
                onSelectedChange = {
                    selectedTabIndex.value =it
                }
            )
        }
    ){
        when(selectedTabIndex.value){
            0 -> {
                HomeScreen(modifier = Modifier.padding(it))
            }
            1->{
                DailyScreen(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun WeatherScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to the Weather App!",
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherApp() {
    WeatherAppTheme {
        WeatherAppScaffold()
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<Tabs>,
    selectedIndex: Int,
    onSelectedChange: (Int) -> Unit,
) {
    NavigationBar(modifier = modifier) {
        tabs.forEachIndexed { index,tab->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {onSelectedChange(index)},
                icon = { Icon(imageVector = tab.icon,contentDescription = tab.title) },
                modifier = modifier,
                label = {
                    Text("${tab.title}")
                },
            )
        }
    }
}
