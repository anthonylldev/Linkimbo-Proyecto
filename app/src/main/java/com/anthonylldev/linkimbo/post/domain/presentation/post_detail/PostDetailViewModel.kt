package com.anthonylldev.linkimbo.post.domain.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postService: PostService
) : ViewModel() {

    private val _post = mutableStateOf<PostResponse?>(null)
    val post: State<PostResponse?> = _post

    fun loadPost(postId: String?) {
        postId?.let {
            viewModelScope.launch {
                _post.value = postService.getPost(it)
            }
        }
    }
}