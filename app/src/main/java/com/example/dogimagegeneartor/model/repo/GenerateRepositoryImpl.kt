package com.example.dogimagegeneartor.model.repo

import com.example.dogimagegeneartor.model.modelclass.DogImageResponse
import com.example.dogimagegeneartor.model.modelclass.NetworkApiResponse
import com.example.dogimagegeneartor.model.network.NetworkApiService

class GenerateRepositoryImpl(private val networkApiService: NetworkApiService) : GenerateDogRepository {
    override suspend fun generateDog() : NetworkApiResponse<DogImageResponse> {
        return try {
            val response = networkApiService.generateDog()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    NetworkApiResponse.Success(data)
                } else {
                    NetworkApiResponse.Error("Empty response body")
                }
            } else {
                NetworkApiResponse.Error(errorMessage = response.message(), errorCode = response.code())
            }
        } catch (e: Exception) {
            NetworkApiResponse.Error(e.message ?: "Unknown error occurred")
        }
    }
}