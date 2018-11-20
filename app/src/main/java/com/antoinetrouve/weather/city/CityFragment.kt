package com.antoinetrouve.weather.city

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.antoinetrouve.weather.App
import com.antoinetrouve.weather.Database
import com.antoinetrouve.weather.R

class CityFragment : Fragment() {

    private lateinit var  cities: MutableList<City>
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf()
        // enable Menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        // Create the menu
        inflater?.inflate(R.menu.fragment_city, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCreateCityDialog() {
        val createCityFragment = CreateCityDialogFragment()
        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener {
            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() { }
        }

        createCityFragment.show(fragmentManager, "CreateCityDialogFragment")
    }

    private fun saveCity(city: City) {
        if (database.CreateCity(city)) {
            cities.add(city)
        } else {
            Toast.makeText(
                context,
                "Could not create city",
                Toast.LENGTH_LONG).show()
        }
    }
}