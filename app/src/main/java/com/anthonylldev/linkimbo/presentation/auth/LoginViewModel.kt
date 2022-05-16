package com.anthonylldev.linkimbo.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val username: State<String> = _usernameText

    private val _usernameError = mutableStateOf("")
    val usernameError: State<String> = _usernameError

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    fun setUsernameText(email: String) {
        _usernameText.value = email
    }

    fun setUsernameError(error: String) {
        _usernameError.value = error
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setPasswordError(error: String) {
        _passwordError.value = error
    }

    fun setPasswordVisibility(visibility: Boolean) {
        _passwordVisibility.value = visibility
    }
}