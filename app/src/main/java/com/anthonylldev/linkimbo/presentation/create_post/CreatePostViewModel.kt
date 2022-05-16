package com.anthonylldev.linkimbo.presentation.create_post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anthonylldev.linkimbo.presentation.util.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor() : ViewModel() {

    private val _postDetail = mutableStateOf(TextFieldState())
    val postDetail: State<TextFieldState> = _postDetail


    fun setPostDetailState(state: TextFieldState) {
        this._postDetail.value = state
    }
}