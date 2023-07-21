package com.hamtz.bengkellas.API

import com.hamtz.bengkellas.Model.ResponseModel
import retrofit2.Call
import retrofit2.http.*


interface APIRequestData {
    @GET("retrieve.php")
    fun ardRetrieveData(): Call<ResponseModel>

    //    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("create.php")
    fun ardCreateData(
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("telepon") telepon: String,
        @Field("panjang") panjang: String,
        @Field("lebar") lebar: String,
        @Field("bahan") bahan: String,
        @Field("ketebalan") ketebalan: String,
        @Field("kode_desain") kode_desain: String,
    ):Call<ResponseModel>
}