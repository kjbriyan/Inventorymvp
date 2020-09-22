package com.example.myinventory.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Initretrofit {
    val NAMA_DOMAIN = "http://192.168.1.168/ci4api/public/"

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NAMA_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getInstance(): ApiInterface {
        return initRetrofit().create(ApiInterface::class.java)
    }


}