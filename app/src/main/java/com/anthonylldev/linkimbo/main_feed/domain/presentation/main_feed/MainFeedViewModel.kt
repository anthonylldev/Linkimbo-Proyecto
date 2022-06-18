package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.activity.application.data.ActivityRequest
import com.anthonylldev.linkimbo.activity.application.service.ActivityService
import com.anthonylldev.linkimbo.post.application.data.LikeRequest
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postService: PostService,
    private val activityService: ActivityService
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _allPosts = mutableStateOf(emptyList<PostResponse>())
    val allPosts: State<List<PostResponse>> = _allPosts

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _allPosts.value = postService.getAllPostSortByTimestamp()
            _isLoading.value = false
            _eventFlow.emit(UiEvent.LoadSuccessful)
        }
    }


    fun like(postId: String, isLiked: Boolean) {
        viewModelScope.launch {
            postService.likePost(postId, LikeRequest(isLiked))

            if (isLiked) {
                val post = postService.getPost(postId)
                val timestamp = System.currentTimeMillis()

                activityService.insertActivity(
                    ActivityRequest(
                        post.user.id, "liked_post", timestamp
                    )
                )
            }

            _eventFlow.emit(UiEvent.Like)
        }
    }
}