package com.anthonylldev.linkimbo.authentication.domain.presentation

import android.app.usage.UsageEvents
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.application.dto.CreateAccountDto
import com.anthonylldev.linkimbo.authentication.application.dto.TokenDto
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
) : ViewModel() {

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

    private val _displayProgressBar = mutableStateOf(false)
    val displayProgressBar: State<Boolean> = _displayProgressBar

    private val _createAccountSuccessful = mutableStateOf(false)
    val createAccountSuccessful: State<Boolean> = _createAccountSuccessful

    private val _eventFlow = MutableSharedFlow<UsageEvents.Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun createAccount() {
        _displayProgressBar.value = true
        viewModelScope.launch {

            val createAccountDto = CreateAccountDto(
                username = usernameText.value,
                email = emailText.value,
                password = passwordText.value
            )

            try {
                authenticationService.createAccount(
                    request = createAccountDto
                ).token

                authenticationService.authenticate()

                _createAccountSuccessful.value = true

                _eventFlow.emit(UsageEvents.Event())

            } catch (e: Exception) {
                _displayProgressBar.value = false
            }
        }
    }

    fun isLoading(): Boolean {
        return _displayProgressBar.value
    }

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