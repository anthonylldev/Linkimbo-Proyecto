package com.anthonylldev.linkimbo.domain.models

data class User(
    val profilePictureUrl: String,
    val username: String,
    val realname: String,
    val description: String,
    val website: String = "",
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)