package com.anthonylldev.linkimbo.post.application.data

import com.anthonylldev.linkimbo.authentication.domain.model.User

data class PostCommentResponse(
    val id: String,
    val user: User,
    val timestamp : Long,
    val comment: String,
    val likeCount: Int,
    val isLiked: Boolean
)