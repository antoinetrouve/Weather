package com.antoinetrouve.weather.city

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.antoinetrouve.weather.R
import com.antoinetrouve.weather.weather.WeatherActivity
import com.antoinetrouve.weather.weather.WeatherFragment

class CityActivity : AppCompatActivity(), CityFragment.CityFragmentListener {
    private lateinit var cityFragment: CityFragment

    private var weatherFragment: WeatherFragment? = null
    private var currentCity: City? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        cityFragment = supportFragmentManager.findFragmentById(R.id.city_fragment) as CityFragment
        cityFragment.listener = this

        weatherFragment = supportFragmentManager.findFragmentById(R.id.weather_fragment) as WeatherFragment?
    }

    override fun onCitySelected(city: City) {
        currentCity = city
        if (isHandsetLayout()) {
            StartWeatherActivity(city)
        } else {
            weatherFragment?.updateWeatherForCity(city.name)
        }
    }

    override fun onEmptyCities() {
        weatherFragment?.clearUi()
    }

    private fun isHandsetLayout(): Boolean = weatherFragment == null

    private fun StartWeatherActivity(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.name)
        startActivity(intent)
    }
}
