package com.antoinetrouve.weather.city

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.antoinetrouve.weather.App
import com.antoinetrouve.weather.Database
import com.antoinetrouve.weather.R
import com.antoinetrouve.weather.utils.toast

class CityFragment : Fragment(), CityAdapter.CityItemListener {

    private lateinit var  cities: MutableList<City>
    private lateinit var database: Database
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  set database instance
        database = App.database
        // enable Menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.cities_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = database.getAllCities()
        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter
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

    override fun onCitySelected(city: City) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCityDeleted(city: City) {
        showDeleteCityDialog(city)
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

    private fun showDeleteCityDialog(city: City) {
        val deleteCityFragment = DeleteCityDialogFragment.newInstance(city.name)

        deleteCityFragment.listener = object: DeleteCityDialogFragment.DeleteCityDialogListener {
            override fun onDialogPositiveClick() {
                deleteCity(city)
            }

            override fun onDialogNegativeClick() { }
        }

        deleteCityFragment.show(fragmentManager, "DeleteDialogFragment")
    }

    private fun saveCity(city: City) {
        if (database.CreateCity(city)) {
            cities.add(city)
            adapter.notifyDataSetChanged()
        } else {
            context?.toast(getString(R.string.citymessage_error_could_not_create))
        }
    }

    private fun deleteCity(city: City) {
        if (database.deleteCity(city)) {
            cities.remove(city)
            adapter.notifyDataSetChanged()
            context?.toast(getString(R.string.citymessage_info_delete, city.name))
        } else {
            context?.toast(getString(R.string.citymessage_error_could_not_delete, city.name))
        }
    }
}
