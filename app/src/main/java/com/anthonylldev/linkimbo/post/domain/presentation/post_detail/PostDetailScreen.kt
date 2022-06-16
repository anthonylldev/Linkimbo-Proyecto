package com.anthonylldev.linkimbo.post.domain.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.post.domain.model.Comment
import com.anthonylldev.linkimbo.post.domain.presentation.PostEvent
import com.anthonylldev.linkimbo.post.domain.presentation.post_simple.ActionRow
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.SpaceExtraSmall
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.theme.SpaceSmall
import com.anthonylldev.linkimbo.util.ui.theme.UnselectedIcons
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
    navController: NavController,
    postId: String?,
    postDetailViewModel: PostDetailViewModel = hiltViewModel()
) {

    postDetailViewModel.loadPost(postId)
    
    LaunchedEffect(key1 = true) {
        postDetailViewModel.eventFlow.collectLatest { event ->
            when(event) {
                is PostEvent.Like -> postDetailViewModel.loadPost(postId)
            }
        }
    }
    
    if (postDetailViewModel.post.value != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            StandarToolbar(
                navController = navController,
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.post_detail),
                showBackArrow = true,
                navActions = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        tint = MaterialTheme.colors.background,
                        modifier = Modifier.padding(SpaceMedium)
                    )
                }
            )

            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Image(
                            bitmap = ImageUtil.base64ToBitmap(postDetailViewModel.post.value!!.imageBase64)!!
                                .asImageBitmap(),
                            contentDescription = null
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(SpaceMedium),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                ActionRow(
                                    username = postDetailViewModel.post.value!!.user.username,
                                    modifier = Modifier.fillMaxWidth(),
                                    isInPostDetail = true,
                                    onLikeClick = { isLiked ->
                                        if (isLiked) {
                                            postDetailViewModel.like()
                                        } else {
                                            postDetailViewModel.unLike()
                                        }
                                    },
                                    onCommentClick = {

                                    },
                                    onShareClick = {

                                    },
                                    onUsernameClick = {
                                        navController.navigate(Screen.ProfileScreen.route + "?userId=${postDetailViewModel.post.value!!.user.id}")
                                    },
                                    isLiked = postDetailViewModel.post.value!!.isLiked
                                )
                                Spacer(modifier = Modifier.height(SpaceSmall))
                                Text(
                                    text = postDetailViewModel.post.value!!.description,
                                    style = MaterialTheme.typography.body2,
                                    color = MaterialTheme.colors.onBackground,
                                )
                                Spacer(modifier = Modifier.height(SpaceMedium))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = stringResource(
                                            id = R.string.liked_by_x_people,
                                            postDetailViewModel.post.value!!.likeCount
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.h2,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = stringResource(
                                            id = R.string.x_comments,
                                            postDetailViewModel.post.value!!.commentCount
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.h2,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }

                items(20) {
                    CommentLayout()
                }
            }
        }
    }
}

@Composable
fun CommentLayout(
    modifier: Modifier = Modifier,
    comment: Comment = Comment()
) {
    Card(
        modifier = modifier
            .padding(SpaceMedium),
        backgroundColor = (MaterialTheme.colors.background),
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anthony_profile_square),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(SpaceMedium))

                Row {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = if (comment.isLiked) {
                            Color.Red
                        } else {
                            UnselectedIcons
                        },
                        modifier = Modifier.clickable { /*TODO*/ }
                    )

                    Spacer(modifier = Modifier.width(SpaceSmall))

                    Text(
                        text = if (comment.likeCount > 99) "+99"
                        else comment.likeCount.toString()
                    )
                }
            }

            Spacer(modifier = Modifier.width(SpaceMedium))

            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                    )

                    Text(
                        text = comment.timestamp.toString(),
                    )
                }

                Spacer(modifier = Modifier.height(SpaceExtraSmall))

                Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}