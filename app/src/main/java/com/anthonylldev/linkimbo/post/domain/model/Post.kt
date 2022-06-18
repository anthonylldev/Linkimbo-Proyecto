package com.anthonylldev.linkimbo.post.domain.model

import com.anthonylldev.linkimbo.authentication.domain.model.User

data class Post(
    val id: String? = null,
    val user: User,
    val imageBase64: String,
    val description: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val timestamp: Long
)
