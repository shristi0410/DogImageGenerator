package com.example.dogimagegeneartor.model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogimagegeneartor.model.database.DogImage
import com.example.dogimagegeneartor.model.modelclass.DogImageResponse
import com.example.dogimagegeneartor.model.modelclass.NetworkApiResponse
import com.example.dogimagegeneartor.model.repo.GenerateRepositoryImpl
import com.example.dogimagegeneartor.model.repo.ImageRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeScreenViewmodel(private val repo: GenerateRepositoryImpl, private val imageRepo: ImageRepository) : ViewModel() {

    private val _apiResponse = MutableSharedFlow<DogImageResponse>()
    val apiResponse = _apiResponse.asSharedFlow()

    private val _allImages = MutableSharedFlow<List<DogImage>>()
    val allImages = _allImages.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()


    fun generateDogs(){
        viewModelScope.launch {
            when(val response = repo.generateDog()){
                is NetworkApiResponse.Success -> {
                    _apiResponse.emit(response.data)
                }
                is NetworkApiResponse.Error -> {
                    _error.emit("Something went wrong")
                }
            }
        }
    }

    fun saveImage(url: String, bitmap: Bitmap) {
        viewModelScope.launch {
            imageRepo.insertImage(url, bitmap)
        }
    }

    fun clearData() {
        viewModelScope.launch {
            imageRepo.clearData()
            _allImages.emit(emptyList())
        }
    }

    fun fetchAllImages() {
        viewModelScope.launch {
            _allImages.emit(imageRepo.getAllImages())
        }
    }

}