package com.anthonylldev.linkimbo.presentation.components


import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
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
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.domain.models.Post
import com.anthonylldev.linkimbo.presentation.ui.theme.HintGray
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.Constants

@Composable
fun Post(
    post: Post,
    profilePictureSize: Dp = 50.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .offset(y = profilePictureSize / 2f)
        ) {

            Image(
                painter = painterResource(id = R.drawable.alf_picture),
                contentDescription = "Post image"
            )

            ActionRow(
                username = "",
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

            Text(
                text = buildAnnotatedString {
                    append(post.description)
                    withStyle(
                        SpanStyle(
                            color = HintGray
                        )
                    ) {
                        append(
                            stringResource(id = R.string.read_more)
                        )
                    }
                },
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = Constants.MAX_POST_DESCRIPTION_LINES
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource (
                        id = R.string.liked_by_x_people,
                        post.likeCount
                    ),
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = stringResource (
                        id = R.string.x_comments,
                        post.commentCount
                    ),
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.profile_picture),
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
                    Color.Red
                } else {
                    Color.White
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
                contentDescription = stringResource(id = R.string.comment)
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
                contentDescription = stringResource(id = R.string.share)
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
        modifier = modifier
    ) {
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
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