package com.anthonylldev.linkimbo.authentication.domain

data class User(
    val profilePictureUrl: String,
    val username: String,
    val realName: String,
    val description: String,
    val website: String = "",
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)