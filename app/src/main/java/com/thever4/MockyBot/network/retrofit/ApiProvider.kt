package com.thever4.MockyBot.network.retrofit

import com.thever4.MockyBot.BuildConfig

class ApiProvider(private val client: RetrofitClient) {
    private val baseApiUrl = "https://61f5894b62f1e300173c41ba.mockapi.io/";
    fun getApi(): Api =
        client
            .getClient(baseApiUrl)
            //.getClient(BuildConfig.BASE_API_URL)
            .create(Api::class.java)
}