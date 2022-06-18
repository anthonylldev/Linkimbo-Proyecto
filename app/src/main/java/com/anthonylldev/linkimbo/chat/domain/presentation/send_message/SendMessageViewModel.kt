package com.anthonylldev.linkimbo.chat.domain.presentation.send_message

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.chat.application.service.MessageService
import com.anthonylldev.linkimbo.chat.domain.model.Message
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMessageViewModel @Inject constructor(
    private val messageService: MessageService,
    private val sharedPreferences: SharedPreferences
): ViewModel() {


    private val _message = mutableStateOf(TextFieldState())
    val message: State<TextFieldState> = _message

    fun setMessageState(state: TextFieldState) {
        this._message.value = state
    }

    fun sendMessage(userReceiverId: String?) {
        val userEmitterId = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)

        userEmitterId?.let {
            userReceiverId?.let {
                viewModelScope.launch {
                    val timestamp = System.currentTimeMillis()

                    messageService.sendMessage(userReceiverId, message = Message(
                        userEmitterId = userEmitterId,
                        userReceiverId = userReceiverId,
                        message = _message.value.text,
                        timestamp = timestamp
                    ))
                }
            }
        }
    }
}