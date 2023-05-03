package com.tananaev.pixabay.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("api")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("safesearch") safeSearch: Boolean = true,
    ): ImageResponse
}
