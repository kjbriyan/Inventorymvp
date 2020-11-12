package com.example.myinventory.network

import com.arsitek.inventorymvp.model.ResponseBarang
import com.arsitek.inventorymvp.model.ResponseLogin
import com.arsitek.inventorymvp.model.ResponseStatus
import com.arsitek.inventorymvp.model.ResponseTransaksi
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("barang/updatestock/{id}")
    fun returen(
        @Path("id") usernameid : String,
        @Field("jumlah_barang") stock: String
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("auth/create/{id}")
    fun regis(
        @Path("id") usernameid : String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name : String
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("auth/auth")
    fun userLogin(
        @Field("uname") email: String,
        @Field("pass") password: String
    ): Call<ResponseLogin>

    @GET("barang/index")
    fun getBarang(): Call<ResponseBarang>

    @GET("transaksi/showtrans/0/1")
    fun getTransaksi(): Call<ResponseTransaksi>

    @GET("transaksi/showtrans/2/3")
    fun getHistory(): Call<ResponseTransaksi>

    @POST("transaksi/filter/{id}")
    fun filter(
        @Path("id") id : String
    ): Call<ResponseTransaksi>

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

    @FormUrlEncoded
    @POST("transaksi/create")
    fun createtransaksi(
        @Field("id_barang") id_barang: String,
        @Field("id_user") id_user: String,
        @Field("keterangan") keterangan: String,
        @Field("pinjam") pinjam : String
    ): Call<ResponseStatus>

    @DELETE("barang/delete/{id}")
    fun deleteitem(
        @Path("id")id : String
    ): Call<ResponseStatus>

    @DELETE("transaksi/delete/{id}")
    fun deletetransaksi(
        @Path("id")id : String
    ): Call<ResponseStatus>


    @FormUrlEncoded
    @POST("transaksi/update/{id}")
    fun updatestatus(
        @Path("id") idTransaksi : String,
        @Field("sta") status: String?
    ): Call<ResponseStatus>


}