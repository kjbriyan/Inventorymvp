package com.example.myinventory.network

import com.arsitek.inventorymvp.model.ResponseBarang
import com.arsitek.inventorymvp.model.ResponseLogin
import com.arsitek.inventorymvp.model.ResponseStatus
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @FormUrlEncoded
    @POST("auth/auth")
    fun userLogin(
        @Field("uname") email: String,
        @Field("pass") password: String
    ): Call<ResponseLogin>


    @GET("barang/index")
    fun getBarang(): Call<ResponseBarang>

    @FormUrlEncoded
    @POST("barang/update/{id}")
    fun updatebarang(
        @Path("id") id : String,
        @Field("nama_barang") namabarang: String,
        @Field("jumlah_barang") jumlah: String,
        @Field("img") img: String?
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("barang/create")
    fun insertbarang(
        @Field("nama_barang") namabarang: String,
        @Field("jumlah_barang") jumlah: String,
        @Field("img") img: String?
    ): Call<ResponseStatus>

    @DELETE("barang/delete/{id}")
    fun deleteitem(
        @Path("id")id : String
    ): Call<ResponseStatus>
}