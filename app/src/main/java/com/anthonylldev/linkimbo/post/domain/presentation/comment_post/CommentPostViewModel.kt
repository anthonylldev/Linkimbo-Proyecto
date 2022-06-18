package com.anthonylldev.linkimbo.post.domain.presentation.comment_post

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.activity.application.data.ActivityRequest
import com.anthonylldev.linkimbo.activity.application.service.ActivityService
import com.anthonylldev.linkimbo.post.application.data.LikeRequest
import com.anthonylldev.linkimbo.post.application.data.PostCommentRequest
import com.anthonylldev.linkimbo.post.application.data.PostCommentResponse
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentPostViewModel @Inject constructor(
    private val postService: PostService,
    private val sharedPreferences: SharedPreferences,
    private val activityService: ActivityService
) : ViewModel() {

    private val _allComments = mutableStateOf(emptyList<PostCommentResponse>())
    val allComments: State<List<PostCommentResponse>> = _allComments

    private val _commentTextState = mutableStateOf(TextFieldState("", ""))
    val commentTextState: State<TextFieldState> = _commentTextState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun setCommentText(state: TextFieldState) {
        this._commentTextState.value = state
    }

    fun comment(postId: String) {
        val userId: String? = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)
        val timestamp = System.currentTimeMillis()

        if (userId != null) {
            viewModelScope.launch {
                postService.commentPost(
                    postId, PostCommentRequest(
                        userId = userId,
                        comment = commentTextState.value.text,
                        timestamp = timestamp
                    )
                )

                val post = postService.getPost(postId)
                val timestamp = System.currentTimeMillis()

                activityService.insertActivity(ActivityRequest(
                    post.user.id, "comment_post", timestamp
                ))

                _eventFlow.emit(UiEvent.Comment)
            }
        }
    }

    fun loadComments(postId: String) {
        viewModelScope.launch {
            _allComments.value = postService.getAllCommentsByPostId(postId)
        }
    }

    fun likeComment(postId: String?, commentId: String, isLiked: Boolean) {
        viewModelScope.launch {
            if (postId != null) {
                postService.likePostComment(
                    postId,
                    commentId,
                    LikeRequest(isLiked)
                )

                if (isLiked) {
                    val post = postService.getPost(postId)
                    val timestamp = System.currentTimeMillis()

                    activityService.insertActivity(ActivityRequest(
                        post.user.id, "liked_comment", timestamp
                    ))
                }

                _eventFlow.emit(UiEvent.Like)
            }
        }
    }
}