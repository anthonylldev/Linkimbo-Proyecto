package com.anthonylldev.linkimbo.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.domain.models.Comment
import com.anthonylldev.linkimbo.domain.models.Post
import com.anthonylldev.linkimbo.presentation.components.ActionRow
import com.anthonylldev.linkimbo.presentation.components.StandarToolbar
import com.anthonylldev.linkimbo.presentation.ui.theme.*

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post = Post(
        username = "Anthony Leon",
        profilePictureUrl = "",
        imageUrl = "",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vehicula dapibus dolor, at mollis ex hendrerit a. Sed a nulla vel velit sollicitudin ultricies imperdiet sit amet magna. Morbi maximus enim sit amet nisl tempus luctus. Nulla malesuada quis diam id imperdiet. Duis efficitur sodales metus, dapibus ornare neque dignissim eget. Nulla quis tellus ex. Mauris egestas mattis leo, sed pellentesque odio condimentum sit amet. Mauris dictum turpis in urna interdum, id auctor sem imperdiet.",
        likeCount = 10,
        commentCount = 3
    )
) {
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

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.post_sample_image),
                contentDescription = "Post image"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    ActionRow(
                        username = post.username,
                        modifier = Modifier.fillMaxWidth(),
                        onLikeClick = { isLiked ->

                        },
                        onCommentClick = {

                        },
                        onShareClick = {

                        },
                        onUsernameClick = { username ->

                        }
                    )
                    Spacer(modifier = Modifier.height(SpaceSmall))
                    Text(
                        text = post.description,
                        style = MaterialTheme.typography.body2,
                        color = Color.Black,
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
        }
    }
}

