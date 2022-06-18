package com.anthonylldev.linkimbo.post.domain.model

import com.anthonylldev.linkimbo.authentication.domain.model.User

data class Comment(
    val id: String,
    val user: User,
    val postId: String,
    val timestamp: Long,
    val comment: String,
    val likeCount: Int = 0,
)
