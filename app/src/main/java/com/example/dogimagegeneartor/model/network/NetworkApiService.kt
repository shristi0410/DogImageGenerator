package com.example.dogimagegeneartor.model.network

import com.example.dogimagegeneartor.model.modelclass.DogImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {

    @GET("breeds/image/random")
    suspend fun generateDog() : Response<DogImageResponse>
}