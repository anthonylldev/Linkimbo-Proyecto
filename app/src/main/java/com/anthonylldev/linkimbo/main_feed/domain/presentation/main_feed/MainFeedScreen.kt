package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.post.domain.presentation.PostEvent
import com.anthonylldev.linkimbo.post.domain.presentation.post_simple.Post
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainFeedScreen(
    navController: NavController,
    mainFeedViewModel: MainFeedViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        mainFeedViewModel.eventFlow.collectLatest { event ->
            when(event) {
                is PostEvent.Like -> mainFeedViewModel.loadPosts()
            }
        }
    }

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
            items(mainFeedViewModel.allPosts.value) { post ->
                Post(
                    post = post,
                    navController = navController,
                    onUsernameClick = {
                        navController.navigate(Screen.ProfileScreen.route + "?userId=${post.user.id}")
                    },
                    onLikeClick = { isLiked ->

                        if (isLiked && post.id != null) {
                            mainFeedViewModel.like(post.id)
                        } else if (post.id != null) {
                            mainFeedViewModel.unLike(post.id)
                        }

                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(75.dp))
            }
        }
    }
}

