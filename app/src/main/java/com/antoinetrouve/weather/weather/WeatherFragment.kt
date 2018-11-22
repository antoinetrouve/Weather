package com.antoinetrouve.weather.weather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antoinetrouve.weather.R
import java.util.zip.Inflater

class WeatherFragment : Fragment() {

    companion object {
        val EXTRA_CITY_NAME = "com.antoinetrouve.weather.extras.EXTRA_CITY_NAME"
        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container,false)

        return view
    }
}