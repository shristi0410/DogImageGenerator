package com.example.dogimagegeneartor.model.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientBuilder {

    fun getRetrofit(): Retrofit {
        val baseUrl = "https://dog.ceo/api/"
        val gsonConvertFact: GsonConverterFactory = GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
        val httpClient: OkHttpClient =
            getHttpClient().build()
        return Retrofit.Builder().baseUrl(baseUrl)
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(gsonConvertFact).client(httpClient).build()
    }

    private fun getHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            readTimeout(120, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
    }

}