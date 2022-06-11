package com.anthonylldev.linkimbo.profile.domain.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.profile.application.service.ProfileService
import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileService: ProfileService
): ViewModel() {

    private val _userId = mutableStateOf<String>("")
    val userId: State<String> = _userId

    private val _profile = mutableStateOf<ProfileResponse?>(null)
    val profile: State<ProfileResponse?> = _profile


    fun setUserId(value: String) {
        _userId.value = value
    }

    fun loadProfile() {
        viewModelScope.launch {
            _profile.value = profileService.loadProfile(_userId.value)
        }
    }
}