package com.example.dogimagegeneartor.model.network

object NetworkApiHelper {
    /**
     * Returns the NetworkApiService instance for making API requests.
     *
     * @return The NetworkApiService instance.
     */
    fun getApiService(
    ): NetworkApiService {
        return RetrofitClientBuilder().getRetrofit().create(NetworkApiService::class.java)
    }
}