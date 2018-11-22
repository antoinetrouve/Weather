package com.antoinetrouve.weather.openweathermap

import com.google.gson.annotations.SerializedName

data class WeatherWrapper(val weather: Array<WeatherData>,
                          val main: MainData)

data class WeatherData(val desciption: String,
                       val icon: String)

class MainData(
    @SerializedName("temp") val temperature: Float,
    val pressure: Int,
    val humidity: Int)
