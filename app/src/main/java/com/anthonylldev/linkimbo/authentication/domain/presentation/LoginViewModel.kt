package com.anthonylldev.linkimbo.authentication.domain.presentation

import android.app.usage.UsageEvents
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.application.data.LoginRequest
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
) : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _usernameError = mutableStateOf("")
    val usernameError: State<String> = _usernameError

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _displayProgressBar = mutableStateOf(false)
    val displayProgressBar: State<Boolean> = _displayProgressBar

    private val _loginSuccessful = mutableStateOf(false)
    val loginSuccessful: State<Boolean> = _loginSuccessful

    private val _eventFlow = MutableSharedFlow<UsageEvents.Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun login() {
        _displayProgressBar.value = true
        viewModelScope.launch {

            val loginRequest = LoginRequest(
                username = usernameText.value,
                password = passwordText.value
            )
            try {
                authenticationService.login(request = loginRequest)

                authenticationService.authenticate()

                _loginSuccessful.value = true

                _eventFlow.emit(UsageEvents.Event())

            } catch (e: Exception) {
                _displayProgressBar.value = false
            }
        }
    }

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