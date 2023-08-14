package com.hamtz.bengkellas.Activity

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamtz.bengkellas.R
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader (private val context: Context) {
    // GSON object responsible for converting from JSON to a Place object
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
    get() = context.resources.openRawResource(R.raw.places)

    /**
     * Reads the list of place JSON objects in the file places.json
     * and returns a list of Place objects
     */
    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlacesResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlacesResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}