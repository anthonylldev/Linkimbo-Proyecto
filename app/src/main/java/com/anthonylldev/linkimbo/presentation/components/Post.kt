package com.anthonylldev.linkimbo.presentation.components


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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.domain.models.Post
import com.anthonylldev.linkimbo.presentation.ui.theme.HintGray
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall
import com.anthonylldev.linkimbo.util.Constants

@Composable
fun Post(
    post: Post,
    profilePictureSize: Dp = 75.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceMedium)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = profilePictureSize / 2f)
                .clip(MaterialTheme.shapes.medium)
                .shadow(5.dp)
                .background(MaterialTheme.colors.primary)
        ) {

            Image(
                painter = painterResource(id = R.drawable.post_sample_image),
                contentDescription = "Post image",
            )

            Column(
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp)
                    .background(MaterialTheme.colors.background)
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = SpaceMedium - 3.dp,
                            end = SpaceMedium - 3.dp,
                            top = SpaceSmall / 2,
                            bottom = SpaceSmall / 2

                        )
                ) {
                    ActionRow(
                        username = post.username,
                        modifier = Modifier.fillMaxWidth(),
                        onLikeClick = { isLiked ->
                            // TODO (on like click)
                        },
                        onCommentClick = {
                            // TODO (on comment click)
                        },
                        onShareClick = {
                            // TODO (on share click)
                        },
                        onUsernameClick = { username ->
                            // TODO (on username click)
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium)
            ) {


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
                        style = MaterialTheme.typography.h2
                    )

                    Text(
                        text = stringResource(
                            id = R.string.x_comments,
                            post.commentCount
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h2
                    )
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.alf_picture),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(profilePictureSize)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun EngagementButtons(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    isLike: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = { onLikeClick(!isLike) },
            modifier = Modifier
                .size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLike) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onBackground
                },
                contentDescription = if (isLike) {
                    stringResource(id = R.string.unlike)
                } else {
                    stringResource(id = R.string.like)
                }
            )
        }

        Spacer(modifier = Modifier.width(SpaceMedium))

        IconButton(
            onClick = { onCommentClick() },
            modifier = Modifier
                .size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Comment,
                contentDescription = stringResource(id = R.string.comment),
                tint = MaterialTheme.colors.onBackground
            )
        }

        Spacer(modifier = Modifier.width(SpaceMedium))

        IconButton(
            onClick = { onShareClick() },
            modifier = Modifier
                .size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    isLike: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    username: String,
    onUsernameClick: (String) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
       modifier = modifier
    ) {
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            ),
            modifier = Modifier
                .clickable {
                    onUsernameClick(username)
                }
        )

        EngagementButtons(
            isLike = isLike,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick
        )
    }
}