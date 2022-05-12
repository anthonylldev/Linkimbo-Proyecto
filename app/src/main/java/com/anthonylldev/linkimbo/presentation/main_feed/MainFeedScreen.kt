package com.anthonylldev.linkimbo.presentation.main_feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.components.Post
import com.anthonylldev.linkimbo.presentation.components.StandarToolbar

@Composable
fun MainFeedScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.your_feed),
            showBackArrow = true,
            navActions = {
                IconButton(onClick = { /*TODO()*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = MaterialTheme.colors.onBackground)
                }
            }
        )

        Post(
            post = com.anthonylldev.linkimbo.domain.models.Post(
                username = "Anthony Leon",
                profilePictureUrl = "",
                imageUrl = "",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vehicula dapibus dolor, at mollis ex hendrerit a. Sed a nulla vel velit sollicitudin ultricies imperdiet sit amet magna. Morbi maximus enim sit amet nisl tempus luctus. Nulla malesuada quis diam id imperdiet. Duis efficitur sodales metus, dapibus ornare neque dignissim eget. Nulla quis tellus ex. Mauris egestas mattis leo, sed pellentesque odio condimentum sit amet. Mauris dictum turpis in urna interdum, id auctor sem imperdiet. Suspendisse et est tristique, fermentum diam nec, faucibus turpis. Etiam vitae metus non arcu porttitor accumsan. In hac habitasse platea dictumst. Duis sit amet fringilla odio, at sollicitudin metus. Nunc laoreet id lectus blandit maximus.",
                likeCount = 10,
                commentCount = 3
            )
        )
    }
}