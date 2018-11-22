package com.antoinetrouve.weather.weather

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // Using the android default fragment: usefull when using only one fragment
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, WeatherFragment.newInstance())
            .commit()
    }
}