package com.example.dogimagegeneartor.model.database
import androidx.room.*

@Dao
interface DogImageCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: DogImage)

    @Query("SELECT * FROM image")
    suspend fun getAllImages(): List<DogImage>

    @Query("DELETE FROM image")
    suspend fun clearAllImages()
}
