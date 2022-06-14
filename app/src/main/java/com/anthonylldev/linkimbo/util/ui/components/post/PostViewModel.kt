package com.anthonylldev.linkimbo.util.ui.components.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.profile.application.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postService: PostService,
    private val userService: UserService
): ViewModel() {

    private val _ownerPostUsername = mutableStateOf("")
    val ownerPostUsername: State<String> = _ownerPostUsername

    private val _ownerPostProfilePicture = mutableStateOf<String?>(null)
    val ownerPostProfilePicture: State<String?> = _ownerPostProfilePicture

    fun loadUsername(userId: String) {
        viewModelScope.launch {
            val user: User = userService.getUserById(userId)
            _ownerPostUsername.value = user.username
            _ownerPostProfilePicture.value = user.imageBase64
        }
    }
}