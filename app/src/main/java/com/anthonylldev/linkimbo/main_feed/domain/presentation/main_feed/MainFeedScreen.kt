package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
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
            when (event) {
                is UiEvent.Like -> mainFeedViewModel.loadPosts()
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

        if (!mainFeedViewModel.isLoading.value) {
            LazyColumn {
                items(mainFeedViewModel.allPosts.value) { post ->
                    Post(
                        post = post,
                        navController = navController,
                        onUsernameClick = {
                            navController.navigate(Screen.ProfileScreen.route + "?userId=${post.user.id}")
                        },
                        onLikeClick = { isLiked ->
                            if (post.id != null) {
                                mainFeedViewModel.like(post.id, isLiked)
                            }
                        },
                        onCommentClick = {
                            navController.navigate(Screen.CommentPostScreen.route + "?postId=${post.id}")
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(75.dp))
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 6.dp,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}

