package com.antoinetrouve.weather.openweathermap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "c3009845562cf85667df1f9ea699ea29"

interface OpenWeatherService {

    @GET("data/2.5/weather?units=metric&lang=fr")
    fun getWeather(@Query("q") cityName: String,
                   @Query("APPID") apiKey: String = API_KEY): Call<WeatherWrapper>
}