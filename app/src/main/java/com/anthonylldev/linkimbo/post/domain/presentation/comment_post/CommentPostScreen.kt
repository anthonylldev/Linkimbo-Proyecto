package com.anthonylldev.linkimbo.post.domain.presentation.comment_post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import com.anthonylldev.linkimbo.post.domain.presentation.post_detail.CommentLayout
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.components.CustomTextField
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommentPostScreen(
    navController: NavController,
    commentPostViewModel: CommentPostViewModel = hiltViewModel(),
    postId: String?
) {
    postId?.let {
        commentPostViewModel.loadComments(postId)

        LaunchedEffect(key1 = true) {
            commentPostViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is UiEvent.Comment -> commentPostViewModel.loadComments(postId)
                    is UiEvent.Like -> commentPostViewModel.loadComments(postId)
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
                title = stringResource(id = R.string.comment_post),
                showBackArrow = true,
                navActions = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = HintGray,
                        modifier = Modifier
                            .padding(SpaceMedium)
                            .clickable {
                                commentPostViewModel.comment(postId)
                            }
                    )
                }
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            CustomTextField(
                text = commentPostViewModel.commentTextState.value.text,
                error = commentPostViewModel.commentTextState.value.error,
                onValueChange = {
                    commentPostViewModel.setCommentText(TextFieldState(it))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        commentPostViewModel.comment(postId)
                    }
                ),
                imeAction = ImeAction.Done,
                maxLength = 255,
                modifier = Modifier.padding(SpaceMedium),
                hint = stringResource(id = R.string.comment)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            LazyColumn {
                items(commentPostViewModel.allComments.value) { comment ->
                    CommentLayout(
                        comment = comment,
                        onLikeClick = { isLiked ->
                            commentPostViewModel.likeComment(postId, comment.id, isLiked)
                        },
                        onUserClick = {
                            navController.navigate(Screen.ProfileScreen.route + "?userId=${comment.user.id}")
                        }
                    )
                }
            }
        }
    }
}