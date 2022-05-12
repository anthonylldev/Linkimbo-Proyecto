package com.anthonylldev.linkimbo.domain.models

data class Comment(
    val commentId: Int = 1,
    val username: String = "Anthony Leon",
    val profilePictureUrl: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val comment: String = "Nullam ut elit elit. Phasellus venenatis sit amet metus id aliquam. Proin sed tincidunt nisi. Pellentesque nec ante eget lectus hendrerit venenatis. Aenean ut consectetur sem. Phasellus diam sem, mattis nec mi sollicitudin, vulputate mollis nisl. Etiam eget convallis tellus.",
    val likeCount: Int = 0,
    val isLiked: Boolean = true
)
