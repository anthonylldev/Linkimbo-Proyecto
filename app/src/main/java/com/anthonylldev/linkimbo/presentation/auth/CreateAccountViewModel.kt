package com.anthonylldev.linkimbo.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _usernameError = mutableStateOf("")
    val usernameError: State<String> = _usernameError

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _emailError = mutableStateOf("")
    val emailError: State<String> = _emailError

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    fun setUsernameText(username: String) {
        _usernameText.value = username
    }

    fun setUsernameError(error: String) {
        _usernameError.value = error
    }

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setEmailError(error: String) {
        _emailError.value = error
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