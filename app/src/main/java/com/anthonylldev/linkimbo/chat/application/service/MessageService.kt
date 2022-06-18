package com.anthonylldev.linkimbo.chat.application.service

import com.anthonylldev.linkimbo.chat.domain.model.Message

interface MessageService {

    suspend fun sendMessage(userId: String, message: Message)
    suspend fun loadMessages(): List<Message>

}