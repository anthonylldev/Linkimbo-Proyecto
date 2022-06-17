package com.anthonylldev.linkimbo.post.domain.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.post.application.data.PostCommentResponse
import com.anthonylldev.linkimbo.util.UiEvent
import com.anthonylldev.linkimbo.post.domain.presentation.post_simple.ActionRow
import com.anthonylldev.linkimbo.util.DateFormatUtil
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

    postId?.let {
        postDetailViewModel.loadPost(postId)
        postDetailViewModel.loadComments(postId)
    }

    if (postDetailViewModel.post.value != null) {


        LaunchedEffect(key1 = true) {
            postDetailViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is UiEvent.Like -> {
                        postDetailViewModel.loadPost(postDetailViewModel.post.value!!.id!!)
                        postDetailViewModel.loadComments(postDetailViewModel.post.value!!.id!!)
                    }
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
                                        postDetailViewModel.like(isLiked)
                                    },
                                    onCommentClick = {
                                        navController.navigate(Screen.CommentPostScreen.route + "?postId=${postDetailViewModel.post.value!!.id}")
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
                                        color = MaterialTheme.colors.onBackground
                                    )
                                    Text(
                                        text = stringResource(
                                            id = R.string.x_comments,
                                            postDetailViewModel.post.value!!.commentCount
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.h2,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }
                            }
                        }
                    }
                }

                items(postDetailViewModel.allComments.value) { comment ->
                    CommentLayout(
                        comment = comment,
                        onLikeClick = { isLiked ->
                            postDetailViewModel.likeComment(comment.id, isLiked)
                        },
                        onUserClick = {
                            navController.navigate(Screen.ProfileScreen.route + "?userId=${postDetailViewModel.post.value!!.user.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CommentLayout(
    modifier: Modifier = Modifier,
    comment: PostCommentResponse,
    onLikeClick: (Boolean) -> Unit,
    onUserClick: () -> Unit
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

                if (comment.user.imageBase64 != null) {
                    Image(
                        bitmap = ImageUtil.base64ToBitmap(comment.user.imageBase64!!)!!
                            .asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                onUserClick()
                            }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.default_profile),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                onUserClick()
                            }
                    )
                }

                Spacer(modifier = Modifier.height(SpaceMedium))

                Row {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = if (comment.isLiked) {
                            Color.Red
                        } else {
                            MaterialTheme.colors.onBackground
                        },
                        modifier = Modifier.clickable {
                            onLikeClick(!comment.isLiked)
                        }
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
                        text = comment.user.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                    )

                    Text(
                        text = DateFormatUtil.timestampToFormattedString(
                            timestamp = comment.timestamp,
                            patter = "MMM dd HH:mm"
                        ),
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