package com.anthonylldev.linkimbo.profile.domain.presentation.edit_profile

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.application.service.UserService
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userService: UserService,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _nameState = mutableStateOf(TextFieldState())
    val nameState: State<TextFieldState> = _nameState

    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    private val _description = mutableStateOf(TextFieldState())
    val description: State<TextFieldState> = _description

    private val _website = mutableStateOf(TextFieldState())
    val website: State<TextFieldState> = _website

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    fun setNameState(state: TextFieldState) {
        this._nameState.value = state
        this._user.value?.realName = state.text
    }

    fun setUsernameState(state: TextFieldState) {
        this._username.value = state
        this._user.value?.username = state.text
    }

    fun setDescriptionState(state: TextFieldState) {
        this._description.value = state
        this._user.value?.description = state.text
    }

    fun setWebsiteState(state: TextFieldState) {
        this._website.value = state
        this._user.value?.website = state.text
    }

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            val userId = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)
            if (userId != null) _user.value = userService.getUserById(userId)

            if (_user.value != null) {
                _username.value = TextFieldState(user.value!!.username)
                _nameState.value = TextFieldState(user.value?.realName!!)
                _description.value = TextFieldState(user.value?.description!!)
                _website.value = TextFieldState(user.value?.website!!)
            }
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            if (_user.value != null) {
                userService.updateUserById(_user.value!!, _user.value!!.id)
            }
        }
    }
}
