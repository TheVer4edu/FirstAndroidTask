package com.thever4.MockyBot.network.retrofit

import com.thever4.MockyBot.network.models.Balance
import com.thever4.MockyBot.network.models.Tariff
import com.thever4.MockyBot.network.models.UserInfo
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("userInfo")
    fun getUserInfo(): Call<List<UserInfo>>

    @GET("tariffs")
    fun getTariffs(): Call<List<Tariff>>

    @GET("balance")
    fun getBalances(): Call<List<Balance>>
}