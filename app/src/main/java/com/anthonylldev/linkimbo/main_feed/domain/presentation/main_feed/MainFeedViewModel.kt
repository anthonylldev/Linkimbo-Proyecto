package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.post.application.data.PostLikeRequest
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.model.Post
import com.anthonylldev.linkimbo.post.domain.presentation.PostEvent
import com.anthonylldev.linkimbo.profile.application.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postService: PostService
) : ViewModel() {

    private val _allPosts = mutableStateOf(emptyList<PostResponse>())
    val allPosts: State<List<PostResponse>> = _allPosts

    private val _eventFlow = MutableSharedFlow<PostEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _allPosts.value = postService.getAllPostSortByTimestamp()
        }
    }


    fun like(postId: String) {
        viewModelScope.launch {
            postService.likePost(postId, PostLikeRequest(true))
            _eventFlow.emit(PostEvent.Like)
        }
    }

    fun unLike(postId: String) {
        viewModelScope.launch {
            postService.likePost(postId, PostLikeRequest(false))
            _eventFlow.emit(PostEvent.Like)
        }
    }
}