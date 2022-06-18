package com.anthonylldev.linkimbo.chat.application.service.impl

import com.anthonylldev.linkimbo.chat.application.service.MessageService
import com.anthonylldev.linkimbo.chat.domain.model.Message
import com.anthonylldev.linkimbo.chat.infrastructure.MessageRestController

class MessageServiceImpl(
    private val api: MessageRestController
) : MessageService {

    override suspend fun sendMessage(userId: String, message: Message) {
        try {
            this.api.createMessage(userId, message)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun loadMessages(): List<Message> {
        try {
            return this.api.getMessages()
        } catch (e: Exception) {
            throw e
        }
    }
}