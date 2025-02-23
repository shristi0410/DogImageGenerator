package com.example.dogimagegeneartor.model.repo

import com.example.dogimagegeneartor.model.modelclass.DogImageResponse
import com.example.dogimagegeneartor.model.modelclass.NetworkApiResponse

interface GenerateDogRepository {
    suspend fun generateDog() : NetworkApiResponse<DogImageResponse>
}