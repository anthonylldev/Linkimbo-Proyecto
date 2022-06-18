package com.anthonylldev.linkimbo.profile.domain.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.profile.application.data.ProfilePostResponse
import com.anthonylldev.linkimbo.profile.application.service.UserService
import com.anthonylldev.linkimbo.profile.application.data.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    private val _posts = mutableStateOf(emptyList<ProfilePostResponse>())
    val posts: State<List<ProfilePostResponse>> = _posts

    private val _userId = mutableStateOf("")
    val userId: State<String> = _userId

    private val _profile = mutableStateOf<ProfileResponse?>(null)
    val profile: State<ProfileResponse?> = _profile


    fun setUserId(value: String) {
        _userId.value = value
    }

    fun loadProfile() {
        viewModelScope.launch {
            _profile.value = userService.loadProfile(_userId.value)
        }
    }

    fun loadPosts(userId: String?) {
        userId?.let {
            viewModelScope.launch {
                _posts.value = userService.loadPosts(userId)
            }
        }
    }
}