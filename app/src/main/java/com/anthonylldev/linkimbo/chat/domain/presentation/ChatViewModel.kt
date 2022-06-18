package com.anthonylldev.linkimbo.chat.domain.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.chat.application.service.MessageService
import com.anthonylldev.linkimbo.chat.domain.model.Message
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageService: MessageService
): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _allMessages = mutableStateOf(emptyList<Message>())
    val allMessages: State<List<Message>> = _allMessages

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun messages() {
        viewModelScope.launch {
            _allMessages.value = messageService.loadMessages()
            _isLoading.value = false
            _eventFlow.emit(UiEvent.LoadSuccessful)
        }
    }

}