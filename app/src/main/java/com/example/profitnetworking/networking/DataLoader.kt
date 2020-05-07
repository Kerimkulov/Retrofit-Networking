package com.example.profitnetworking.networking

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataLoader (
     private val onSuccess: (FactResponse) -> Unit,
     private val onError: (Throwable) -> Unit,
     private val key: String
){

    fun loadData(){
        ApiFactory.getApiService()
            .getAllDataByKey(key)
            .enqueue(object : Callback<FactResponse> {
                override fun onResponse(
                    call: Call<FactResponse>,
                    response: Response<FactResponse>
                ) {
                    onSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<FactResponse>, t: Throwable) {
                    onError(t)
                }
            })
    }
}