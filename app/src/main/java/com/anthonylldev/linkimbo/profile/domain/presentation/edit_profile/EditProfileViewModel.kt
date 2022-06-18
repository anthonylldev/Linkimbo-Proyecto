package com.anthonylldev.linkimbo.profile.domain.presentation.edit_profile

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.application.service.UserService
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userService: UserService,
    private val sharedPreferences: SharedPreferences,
    private val application: Application
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

    private val _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri

    private val _bitmap = mutableStateOf<Bitmap?>(null)
    val bitmap: State<Bitmap?> = _bitmap

    private val _postDetail = mutableStateOf(TextFieldState())
    val postDetail: State<TextFieldState> = _postDetail

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val userId = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)
            if (userId != null) _user.value = userService.getUserById(userId)

            if (_user.value != null) {
                if (user.value!!.imageBase64 != null) {
                    _bitmap.value = ImageUtil.base64ToBitmap(user.value!!.imageBase64!!)
                }
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

    fun setImageUriState(state: Uri?) {
        this._imageUri.value = state

        if (this._imageUri.value != null) {
            setBitmapState(this._imageUri.value!!)
        }
    }

    private fun setBitmapState(uri: Uri) {
        if (Build.VERSION.SDK_INT < 28) {
            this._bitmap.value = MediaStore.Images
                .Media.getBitmap(application.contentResolver, uri)

        } else {
            val source = ImageDecoder
                .createSource(application.contentResolver, uri)
            this._bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        this._user.value?.imageBase64 = ImageUtil.bitmapToBase64(this._bitmap.value!!)
    }

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

    @SuppressLint("CommitPrefEdits")
    fun closeSession(): Boolean {
        sharedPreferences.edit().clear().apply()
        return true
    }
}
