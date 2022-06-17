package com.anthonylldev.linkimbo.post.domain.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.post.application.data.PostCommentResponse
import com.anthonylldev.linkimbo.post.application.data.PostLikeRequest
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.presentation.PostEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postService: PostService
) : ViewModel() {

    private val _allComments = mutableStateOf(emptyList<PostCommentResponse>())
    val allComments: State<List<PostCommentResponse>> = _allComments

    private val _post = mutableStateOf<PostResponse?>(null)
    val post: State<PostResponse?> = _post

    private val _eventFlow = MutableSharedFlow<PostEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun loadPost(postId: String) {
        viewModelScope.launch {
            _post.value = postService.getPost(postId)
        }
    }

    fun loadComments(postId: String) {
        viewModelScope.launch {
            _allComments.value = postService.getAllCommentsByPostId(postId)
        }

    }

    fun like() {
        viewModelScope.launch {
            if (_post.value?.id != null) {
                postService.likePost(_post.value!!.id!!, PostLikeRequest(true))
                _eventFlow.emit(PostEvent.Like)
            }
        }
    }

    fun unLike() {
        viewModelScope.launch {
            if (_post.value?.id != null) {
                postService.likePost(_post.value!!.id!!, PostLikeRequest(false))
                _eventFlow.emit(PostEvent.Like)
            }
        }
    }
}