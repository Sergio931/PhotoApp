package com.sergio931.photoapp.presentation.ui

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergio931.photoapp.R
import com.sergio931.photoapp.domain.model.Photo
import com.sergio931.photoapp.ui.theme.Teal200
import coil.compose.AsyncImage
import com.sergio931.photoapp.ui.theme.CardBg

@Composable
fun AppBarComponent(
    title: String,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Teal200,
        elevation = 2.dp
    ) {

        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PhotoListItem(
    photo: Photo
) {
    Card(modifier = Modifier) {

        Box(
            modifier = Modifier
                .background(color = CardBg)
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier, elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                    ) {
                        AsyncImage(
                            model = photo.urlPhoto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = photo.description ?: "", color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = photo.urlPhotoUser,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(Modifier.size(16.dp))
                    Column {
                        Text(
                            photo.userName,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

        }
    }

}