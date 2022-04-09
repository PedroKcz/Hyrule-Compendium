package com.hyrule.imageloader

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageLoader(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    @DrawableRes placeholder: Int? = null,
    @DrawableRes fallback: Int? = null,
    @DrawableRes error: Int? = null,
    contentScale: ContentScale = ContentScale.Fit,
) = AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .build(),
    placeholder = placeholder?.let { painterResource(id = it) },
    fallback = fallback?.let { painterResource(id = it) },
    error = error?.let { painterResource(id = it) },
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = contentScale
)
