package com.antoinetrouve.weather

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.antoinetrouve.weather.city.City

/** Database name */
private const val DATABASE_NAME = "weather.db"
/** Database version */
private const val DATABASE_VERSION = 1

/** Table name*/
private const val CITY_TABLE_NAME = "city"
/** Table column identifier */
private const val CITY_KEY_ID = "id"
/** Table column name */
private const val CITY_KEY_NAME = "name"

/** Query to create Table City */
private const val CITY_TABLE_CREATE = """
CREATE TABLE $CITY_TABLE_NAME (
    $CITY_KEY_ID INTEGER PRIMARY KEY,
    $CITY_KEY_NAME TEXT
)
"""
/** Query to get all cities */
private const val CITY_QUERY_SELECT_ALL = "SELECT * FROM $CITY_TABLE_NAME"

/**
 * Class Database to manage local database
 * Heritage : SQLiteOpenHelper
 */
class Database(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // for debug purpose
    val TAG = Database::class.java.simpleName

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CITY_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {  }

    /**
     * Insert city in database
     * @return the city identifier created
     */
    fun CreateCity(city: City) : Boolean {
        val values = ContentValues()
        values.put(CITY_KEY_NAME, city.name)

        Log.d(TAG, "Creating city : $values .")

        val id: Long = writableDatabase.insert(CITY_TABLE_NAME, null, values)
        city.id = id

        return id > 0
    }

    fun getAllCities(): MutableList<City> {
        val cities = mutableListOf<City>()
        readableDatabase.rawQuery(CITY_QUERY_SELECT_ALL, null).use { cursor ->
            while (cursor.moveToNext()) {
                val city = City(
                    cursor.getLong(cursor.getColumnIndex(CITY_KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(CITY_KEY_NAME))
                )

                cities.add(city)
            }
        }
        return cities
    }

    fun deleteCity(city: City): Boolean {
        Log.d(TAG, "Delete city $city")
        val deleteCount = writableDatabase.delete(
            CITY_TABLE_NAME,
            "$CITY_KEY_ID = ?",
            arrayOf("${city.id}")
        )

        return deleteCount == 1
    }

}
