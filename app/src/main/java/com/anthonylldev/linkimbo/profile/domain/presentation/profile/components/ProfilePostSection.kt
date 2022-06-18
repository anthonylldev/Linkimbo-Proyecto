package com.anthonylldev.linkimbo.profile.domain.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.profile.application.data.ProfilePostResponse
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.theme.ProfileSize
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium

@Composable
fun ProfilePostSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    posts: List<ProfilePostResponse>
) {
    repeat(posts.size) {
        Box(
            modifier = Modifier
                .width(
                    (LocalConfiguration.current.screenWidthDp.dp) / 3
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                bitmap = ImageUtil.base64ToBitmap(posts[it].imageBase64)!!.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .border(1.dp, Color.White)
                    .clickable {
                        navController.navigate(Screen.PostDetailScreen.route + "?postId=${posts[it].id}")
                    },
            )
        }
    }
}
