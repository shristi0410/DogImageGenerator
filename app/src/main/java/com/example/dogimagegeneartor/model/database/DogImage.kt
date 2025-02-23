package com.example.dogimagegeneartor.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image")
data class DogImage(
    @PrimaryKey val url: String,
    val imageData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DogImage

        if (url != other.url) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}