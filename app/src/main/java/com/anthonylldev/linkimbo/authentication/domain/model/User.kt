package com.anthonylldev.linkimbo.authentication.domain.model

data class User(
    val id: String,
    var username: String,
    val email: String,
    val password: String,
    var realName: String? = "",
    val profilePictureUrl: String? = null,
    var description: String? = "",
    var website: String? = "",
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val postCount: Int = 0,
)