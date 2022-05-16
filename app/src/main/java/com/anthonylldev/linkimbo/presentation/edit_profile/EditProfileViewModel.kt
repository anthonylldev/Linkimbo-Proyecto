package com.anthonylldev.linkimbo.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anthonylldev.linkimbo.presentation.util.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {

    private val _nameState = mutableStateOf(TextFieldState())
    val nameState: State<TextFieldState> = _nameState

    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    private val _biography = mutableStateOf(TextFieldState())
    val biography: State<TextFieldState> = _biography

    private val _website = mutableStateOf(TextFieldState())
    val website: State<TextFieldState> = _website

    fun setNameState(state: TextFieldState) {
        this._nameState.value = state
    }

    fun setUsernameState(state: TextFieldState) {
        this._username.value = state
    }

    fun setBiographyState(state: TextFieldState) {
        this._biography.value = state
    }

    fun setWebsiteState(state: TextFieldState) {
        this._website.value = state
    }
}
