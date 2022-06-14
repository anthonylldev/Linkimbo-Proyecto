package com.anthonylldev.linkimbo.post.domain.model

data class Post(
    val id: String,
    val userId: String,
    val imageUrl: String? = null,
    val description: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    var timestamp: Long
)
