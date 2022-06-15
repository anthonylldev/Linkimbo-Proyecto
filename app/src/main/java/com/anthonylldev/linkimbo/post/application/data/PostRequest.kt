package com.anthonylldev.linkimbo.post.application.data


data class PostRequest(
    val userId: String,
    val imageBase64: String,
    val description: String,
    val timestamp: Long
)
