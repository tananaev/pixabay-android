package com.tananaev.pixabay.data

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ImageRepository @Inject constructor(
    @ApplicationContext context: Context,
) {

    private val key = "35995529-af7268b8d8c7fa31271b074aa"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ImageService::class.java)

    private val database = Room.databaseBuilder(context, ImageDatabase::class.java, "database-name").build()

    private val imageDao = database.imageDao()

    fun getImages(): Flow<List<ImageItem>> = flow {
        val data = service.getImages(key)
        imageDao.addImages(data.hits)
        emit(data)
    }
        .map { it.hits }
        .onStart { emit(imageDao.getImages()) }

}
