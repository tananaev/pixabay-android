package com.tananaev.pixabay.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images")
data class ImageItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "preview_url")
    @SerializedName("previewURL")
    val previewUrl: String,
)

data class ImageResponse(
    @SerializedName("hits")
    val hits: List<ImageItem>,
)
