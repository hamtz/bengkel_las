package com.hamtz.bengkellas.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DataUtilities {

        private val baseURL = "http://192.168.1.15:8080/bengkelasri/"
        private lateinit var retro: Retrofit

        fun getAPIRequestData(): APIRequestData? {
            if (retro == null) {
                retro = Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retro.create(APIRequestData::class.java)
        }
}



//    companion object {
//        fun konekRetrofit(): Any {
//
//        }
//    }
