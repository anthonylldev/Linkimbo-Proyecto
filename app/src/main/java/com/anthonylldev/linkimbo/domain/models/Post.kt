package com.anthonylldev.linkimbo.domain.models

data class Post(
    val username: String,
    val profilePictureUrl: String,
    val imageUrl: String,
    val description: String,
    val likeCount: Int,
    val commentCount: Int
)
