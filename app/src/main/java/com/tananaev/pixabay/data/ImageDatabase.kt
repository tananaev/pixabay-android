package com.tananaev.pixabay.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    suspend fun getImages(): List<ImageItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<ImageItem>)
}

@Database(entities = [ImageItem::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
