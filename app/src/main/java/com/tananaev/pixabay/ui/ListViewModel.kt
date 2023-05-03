package com.tananaev.pixabay.ui

import androidx.lifecycle.ViewModel
import com.tananaev.pixabay.data.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {
    fun getImages() = imageRepository.getImages()
}
