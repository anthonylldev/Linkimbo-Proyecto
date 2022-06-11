package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.components.Post
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.navigation.Screen

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
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.profile_icon),
                    tint = HintGray,
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .clickable {
                            navController.navigate(Screen.ProfileScreen.route + "?userId=${"me"}")
                        }
                )
            }
        )

        Post(
            post = com.anthonylldev.linkimbo.post.domain.model.Post(
                username = "Anthony Leon",
                profilePictureUrl = "",
                imageUrl = "",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vehicula dapibus dolor, at mollis ex hendrerit a. Sed a nulla vel velit sollicitudin ultricies imperdiet sit amet magna. Morbi maximus enim sit amet nisl tempus luctus. Nulla malesuada quis diam id imperdiet. Duis efficitur sodales metus, dapibus ornare neque dignissim eget. Nulla quis tellus ex. Mauris egestas mattis leo, sed pellentesque odio condimentum sit amet. Mauris dictum turpis in urna interdum, id auctor sem imperdiet. Suspendisse et est tristique, fermentum diam nec, faucibus turpis. Etiam vitae metus non arcu porttitor accumsan. In hac habitasse platea dictumst. Duis sit amet fringilla odio, at sollicitudin metus. Nunc laoreet id lectus blandit maximus.",
                likeCount = 10,
                commentCount = 3
            ),
            onPostClick = { navController.navigate(Screen.PostDetailScreen.route) }
        )
    }
}