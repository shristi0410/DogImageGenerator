package com.example.dogimagegeneartor.model.repo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.dogimagegeneartor.model.database.DogImage
import com.example.dogimagegeneartor.model.database.DogImageCacheDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class ImageRepository(private val imageDao: DogImageCacheDao) {

    suspend fun insertImage(url: String, bitmap: Bitmap) {
        val byteArray = bitmapToByteArray(bitmap)
        val imageEntity = DogImage(url = url, imageData = byteArray)
        withContext(Dispatchers.IO) {
            imageDao.insertImage(imageEntity)
        }
    }

    suspend fun clearData() {
        return withContext(Dispatchers.IO) {
            imageDao.clearAllImages()
        }
    }

    suspend fun getAllImages(): List<DogImage> {
        return withContext(Dispatchers.IO) {
            imageDao.getAllImages()
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}

