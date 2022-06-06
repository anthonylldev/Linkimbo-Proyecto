package com.anthonylldev.linkimbo.main_feed.domain.model

data class Post(
    val username: String,
    val profilePictureUrl: String,
    val imageUrl: String,
    val description: String,
    val likeCount: Int,
    val commentCount: Int
)
