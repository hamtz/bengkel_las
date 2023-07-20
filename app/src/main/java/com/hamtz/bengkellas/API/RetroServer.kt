package com.hamtz.bengkellas.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroServer {
//        private val baseURL = "http://192.168.1.15:8080/bengkelasri/"

//        private lateinit var retro: Retrofit

//        fun getDataAPI(): APIRequestData {
//            if (retro == null) {
//                retro == Retrofit.Builder().baseUrl(baseURL)
//                    .addConverterFactory(GsonConverterFactory.create()).build()
//            }
//            return retro.create(APIRequestData::class.java)
//        }

    companion object {
        private val baseURL = "http://192.168.43.215:8080/bengkelasri/"
        private var retro: Retrofit? = null

        fun konekRetrofit(): Retrofit {
            if (retro == null) {
                retro = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retro!!
        }
    }

}