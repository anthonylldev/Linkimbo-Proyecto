package com.anthonylldev.linkimbo.post.domain.presentation.comment_post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentPostViewModel @Inject constructor(

): ViewModel() {

    private val _commentTextState = mutableStateOf(TextFieldState("", ""))
    val commentTextState: State<TextFieldState> = _commentTextState

    fun setCommentText(state: TextFieldState) {
        this._commentTextState.value = state
    }

    fun comment(postId: String?) {
        postId?.let {
            // TODO (Service)
        }
    }

}