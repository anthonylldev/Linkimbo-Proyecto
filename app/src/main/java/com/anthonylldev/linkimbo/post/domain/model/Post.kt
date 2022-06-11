package com.anthonylldev.linkimbo.post.domain.model

data class Post(
    val username: String,
    val profilePictureUrl: String,
    val imageUrl: String,
    val description: String,
    val likeCount: Int,
    val commentCount: Int
)
