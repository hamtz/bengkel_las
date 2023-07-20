package com.hamtz.bengkellas.API

import com.hamtz.bengkellas.Model.DataModel
import com.hamtz.bengkellas.Model.ResponseModel
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET




interface APIRequestData {
    @GET("retrieve.php")
//    Call<ResponseModel> ardRetrieveData()
    fun ardRetrieveData(): Call<ResponseModel>
//     fun ardRetrieveData():Response<List<ResponseModel>>
}