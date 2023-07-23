package com.example.trevomobile.network

import com.example.trevomobile.api.EndPoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {
    val BASE_URL = "http://192.168.0.109:8080/trevo/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productservice = retrofit.create(EndPoints::class.java)


}

