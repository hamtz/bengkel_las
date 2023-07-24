package com.hamtz.bengkellas.API

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroServer {

    companion object {
        private val baseURL = "http://192.168.43.215:8080/bengkelasri/"
//        private val baseURL = "http://xampp.mamanialaundry.my.id:8080/bengkelasri/"
//        private val baseURL = "http://192.168.1.17:8080/bengkelasri/"
        private var retro: Retrofit? = null
//
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun konekRetrofit(): Retrofit {
            if (retro == null) {
                retro = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retro!!
        }
    }

}