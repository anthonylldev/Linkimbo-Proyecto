package com.anthonylldev.linkimbo.chat.infrastructure

import com.anthonylldev.linkimbo.chat.domain.model.Message
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageRestController {

    @POST("/chat/{userId}")
    suspend fun createMessage(
        @Path("userId") userId: String,
        @Body message: Message
    )

    @GET("/chat")
    suspend fun getMessages(): List<Message>
}