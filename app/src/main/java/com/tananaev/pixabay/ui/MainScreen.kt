package com.tananaev.pixabay.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tananaev.pixabay.R
import com.tananaev.pixabay.data.ImageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    listViewModel: ListViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            SmallTopAppBar({
                Text(stringResource(id = R.string.app_name))
            })
        },
    ) {
        val images = listViewModel.getImages().collectAsState(listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            items(images.value) { ImageListItem(it) }
        }
    }
}

@Composable
fun ImageListItem(image: ImageItem) {
    ListItem(
        headlineContent = {
            Text("${image.id}")
        },
        leadingContent = {
            AsyncImage(
                model = image.previewUrl,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
    )
}
