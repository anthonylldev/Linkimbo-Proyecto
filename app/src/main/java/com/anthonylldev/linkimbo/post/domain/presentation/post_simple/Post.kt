package com.anthonylldev.linkimbo.post.domain.presentation.post_simple

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.domain.model.Post
import com.anthonylldev.linkimbo.post.domain.presentation.PostEvent
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Post(
    modifier: Modifier = Modifier,
    post: PostResponse,
    navController: NavController,
    showProfileImage: Boolean = true,
    onUsernameClick: () -> Unit,
    onLikeClick: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = SpaceMedium,
                start = SpaceMedium,
                end = SpaceMedium,
                bottom = SpaceExtraLarge,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(
                    y = if (showProfileImage) {
                        ProfileSize / 2f
                    } else 0.dp
                )
                .clip(MaterialTheme.shapes.medium)
                .shadow(5.dp)
                .background(Color.LightGray)
                .clickable {
                    navController.navigate(Screen.PostDetailScreen.route + "?postId=${post.id}")
                }
        ) {
            Image(
                bitmap = ImageUtil.base64ToBitmap(post.imageBase64)!!.asImageBitmap(),
                contentDescription = null)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium)
            ) {
                ActionRow(
                    modifier = Modifier.fillMaxWidth(),
                    onLikeClick = onLikeClick,
                    onCommentClick = {

                    },
                    onShareClick = {

                    },
                    username = post.user.username,
                    onUsernameClick = onUsernameClick,
                    isLiked = post.isLiked
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = buildAnnotatedString {
                        append(post.description)
                        withStyle(
                            SpanStyle(
                                color = HintGray,
                            )
                        ) {
                            append(
                                LocalContext.current.getString(
                                    R.string.read_more
                                )
                            )
                        }
                    },
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    maxLines = Constants.MAX_POST_DESCRIPTION_LINES
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
                            post.likeCount
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h2,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(
                            id = R.string.x_comments,
                            post.commentCount
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h2,
                        color = Color.Black
                    )
                }
            }
        }
        if (showProfileImage) {
            if (post.user.imageBase64 != null) {
                Image(
                    bitmap = ImageUtil.base64ToBitmap(post.user.imageBase64!!)!!.asImageBitmap(),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(ProfileSize)
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.default_profile),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(ProfileSize)
                        .clip(CircleShape)
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun EngagementButtons(
    modifier: Modifier = Modifier,
    isInPostDetail: Boolean,
    isLiked: Boolean,
    iconSize: Dp = 30.dp,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                onLikeClick(!isLiked)
            },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLiked) {
                    Color.Red
                } else {
                    if (isInPostDetail) {
                        MaterialTheme.colors.onBackground
                    } else {
                        UnselectedIcons
                    }
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.unlike)
                } else {
                    stringResource(id = R.string.like)
                }
            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))
        IconButton(
            onClick = {
                onCommentClick()
            },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Comment,
                contentDescription = stringResource(id = R.string.comment),
                tint = if (isInPostDetail) {
                    MaterialTheme.colors.onBackground
                } else {
                    UnselectedIcons
                }
            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))
        IconButton(
            onClick = {
                onShareClick()
            },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share),
                tint = if (isInPostDetail) {
                    MaterialTheme.colors.onBackground
                } else {
                    UnselectedIcons
                }
            )
        }
    }
}

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    isInPostDetail: Boolean = false,
    isLiked: Boolean,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    username: String,
    onUsernameClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .clickable {
                    onUsernameClick()
                }
        )
        EngagementButtons(
            isInPostDetail = isInPostDetail,
            isLiked = isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick
        )
    }
}