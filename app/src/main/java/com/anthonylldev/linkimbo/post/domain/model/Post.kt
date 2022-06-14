package com.anthonylldev.linkimbo.post.domain.model

data class Post(
    val id: String? = null,
    val userId: String,
    val imageBase64: String,
    val description: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val timestamp: Long
)
