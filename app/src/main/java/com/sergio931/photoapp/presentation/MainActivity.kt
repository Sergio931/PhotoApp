package com.sergio931.photoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.sergio931.photoapp.PhotoApp
import com.sergio931.photoapp.presentation.ui.HomeScreen
import com.sergio931.photoapp.presentation.viewmodel.HomeScreenViewModel
import com.sergio931.photoapp.ui.theme.PhotoAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as PhotoApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, modelFactory)[HomeScreenViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            PhotoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

