package com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postService: PostService
): ViewModel() {

    private val _allPosts = mutableStateOf(emptyList<Post>())
    val allPosts: State<List<Post>> = _allPosts

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _allPosts.value = postService.getAllPostSortByTimestamp()
        }
    }

}