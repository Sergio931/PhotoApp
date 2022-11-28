package com.sergio931.photoapp.common

import com.sergio931.photoapp.data.dto.PhotoDto
import com.sergio931.photoapp.domain.model.Photo

fun PhotoDto.toPhoto(): Photo {
    return Photo(
        id = id,
        description = description,
        urlPhoto = urls.regular,
        userName = user.name,
        urlPhotoUser = user.profile_image.small,
    )
}