package com.sergio931.photoapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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


/*
Photo listing view
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotosListingComponent(
    itemList: MutableList<Photo>,
    homeViewModel: HomeScreenViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList.size) { i ->
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        itemList.remove(itemList[i])
                        homeViewModel.deletePhoto(itemList.toList())
                    }

                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                background = {
                    val color = when (dismissState.dismissDirection) {
                        DismissDirection.StartToEnd -> {
                            Color.Transparent
                        }
                        DismissDirection.EndToStart -> {
                            val r = 1f
                            var g = 1f - (abs(dismissState.offset.value) / 255f) * 0.5f
                            var b = 1f - (abs(dismissState.offset.value) / 255f) * 0.5f
                            if (g <= 0f) {
                                g = 0f
                            }
                            if (b <= 0f) {
                                b = 0f
                            }
                            Color(red = r, green = g, blue = b)
                        }
                        else -> {
                            Color.Transparent
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(10.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, null, tint = Color.White)
                    }
                },
                directions = setOf(DismissDirection.EndToStart),
                dismissContent = {

                    PhotoListItem(photo = itemList[i])
                },
            )
        }

    }
}