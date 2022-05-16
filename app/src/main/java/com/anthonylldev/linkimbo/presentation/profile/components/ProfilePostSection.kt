package com.anthonylldev.linkimbo.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.util.Screen

@Composable
fun ProfilePostSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    posts: List<Painter> = listOf(
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
        painterResource(id = R.drawable.post_sample_image),
    )
) {
    repeat(posts.size) {
        Box(
            modifier = Modifier
                .width(
                    (LocalConfiguration.current.screenWidthDp.dp) / 3
                )
                .clickable {
                    navController.navigate(Screen.PostDetailScreen.route)
                },
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = posts[it],
                contentDescription = "",
                modifier = Modifier
                    .border(1.dp, Color.White)
            )
        }
    }
}
