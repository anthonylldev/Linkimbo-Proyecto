package com.anthonylldev.linkimbo.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anthonylldev.linkimbo.R

@Composable
fun AuthImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            if (isSystemInDarkTheme()) {
                R.drawable.social_sapce_white
            } else {
                R.drawable.social_space_black
            }
        ),
        contentDescription = "authImage",
        contentScale = ContentScale.Inside,
        modifier = modifier
            .height(275.dp)
            .width(275.dp)
    )
}