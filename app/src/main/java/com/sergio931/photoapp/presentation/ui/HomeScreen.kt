package com.sergio931.photoapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.sergio931.photoapp.R
import com.sergio931.photoapp.domain.model.Photo
import com.sergio931.photoapp.presentation.viewmodel.HomeScreenViewModel
import com.sergio931.photoapp.ui.theme.AppBg
import kotlin.math.abs

@Composable
fun HomeScreen(
    homeViewModel: HomeScreenViewModel = viewModel()
) {
    val state = remember { homeViewModel.state }
    val isRefreshing = SwipeRefreshState(isRefreshing = state.value.isLoading)

    Scaffold(
        modifier = Modifier,
        isFloatingActionButtonDocked = true,
        backgroundColor = AppBg,
        topBar = {
            AppBarComponent(
                title = stringResource(id = R.string.app_name)
            )
        }
    ) {
        it
        SwipeRefresh(
            state = isRefreshing,
            onRefresh = {
                homeViewModel.onEvent(HomeScreenEvent.GetPhotosList)
            },
        ) {
            PhotosListingComponent(
                itemList = state.value.photosList.toMutableList(),
                homeViewModel
            )
        }

        if (state.value.isError && state.value.photosList.isEmpty()) {
            FailureScreen(onRetryClicked = {
                homeViewModel.onEvent(
                    HomeScreenEvent.GetPhotosList
                )
            })
        }
        if (state.value.isEmpty) {
            EmptyScreen(stringResource(R.string.photo_empty_text))
        }
    }


}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotosListingComponent(
    itemList: MutableList<Photo>,
    homeViewModel: HomeScreenViewModel
) {
    var photoList = itemList.toMutableStateList()
    LazyColumn(
        contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(items = photoList) { index,item ->
            PhotoListItem(photo = item,
            onDeleteClick = {
                photoList.remove(item)
                homeViewModel.deletePhoto(photoList)
            })
        }

    }
}