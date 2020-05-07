package com.example.profitnetworking.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getAllDataByKey(@Query("key") key: String): Call<FactResponse>
}