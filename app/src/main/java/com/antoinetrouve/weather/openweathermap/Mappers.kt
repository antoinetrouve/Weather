package com.antoinetrouve.weather.openweathermap

import com.antoinetrouve.weather.weather.Weather

fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper) : Weather {
    val weather = weatherWrapper.weather.first()
    return Weather(
        description = weather.description,
        temperature = weatherWrapper.main.temperature,
        humidity = weatherWrapper.main.humidity,
        pressure = weatherWrapper.main.pressure,
        iconUrl = "https://openweathermap.org/img/w/${weather.icon}.png"
    )
}