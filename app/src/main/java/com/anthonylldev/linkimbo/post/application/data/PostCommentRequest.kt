package com.anthonylldev.linkimbo.post.application.data

data class PostCommentRequest(
    val userId: String,
    val comment: String,
    val timestamp: Long
)