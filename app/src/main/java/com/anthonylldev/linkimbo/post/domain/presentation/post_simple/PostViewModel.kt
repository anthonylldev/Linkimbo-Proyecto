package com.anthonylldev.linkimbo.post.domain.presentation.post_simple

import androidx.lifecycle.ViewModel
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.profile.application.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postService: PostService,
    private val userService: UserService
): ViewModel() {

}