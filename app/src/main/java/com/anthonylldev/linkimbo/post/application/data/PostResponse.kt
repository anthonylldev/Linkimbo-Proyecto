package com.anthonylldev.linkimbo.post.application.data

import com.anthonylldev.linkimbo.authentication.domain.model.User

data class PostResponse(
    val id: String?,
    val user: User,
    val imageBase64: String,
    val description: String,
    val likeCount: Int,
    val isLiked: Boolean,
    val commentCount: Int,
    val timestamp: Long
)