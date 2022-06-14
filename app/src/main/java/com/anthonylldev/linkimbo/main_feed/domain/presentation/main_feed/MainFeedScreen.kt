package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.components.post.Post
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.theme.*

@Composable
fun MainFeedScreen(
    navController: NavController,
    viewModel: MainFeedViewModel = hiltViewModel()
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

        LazyColumn {
            items(viewModel.allPosts.value) { post ->
                Post(
                    post = post,
                    onPostClick = { navController.navigate(Screen.PostDetailScreen.route) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(75.dp))
            }
        }
    }
}

