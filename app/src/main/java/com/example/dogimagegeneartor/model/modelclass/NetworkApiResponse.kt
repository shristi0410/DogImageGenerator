package com.example.dogimagegeneartor.model.modelclass

sealed class NetworkApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkApiResponse<T>()
    data class Error(val errorMessage: String, val errorCode: Int? = null) : NetworkApiResponse<Nothing>()
}