package com.hamtz.bengkellas.API

import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.Model.ResponseUserModel
import retrofit2.Call
import retrofit2.http.*


interface APIRequestData {
    @GET("retrieve_user.php")
    fun apiRetrieveUserData(): Call<ResponseUserModel>

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
        @Field ("status_pesanan")status_pesanan: String,
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("delete.php")
    fun ardDeleteData(
        @Field("id") id: Int
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("get.php")
    fun ardGetData(
        @Field("id") id: Int
    ):Call<ResponseModel>

    @FormUrlEncoded
    @POST("update.php")
    fun ardUpdateData(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("telepon") telepon: String,
        @Field("status_pesanan") status_pesanan: String,
    ):Call<ResponseModel>
}