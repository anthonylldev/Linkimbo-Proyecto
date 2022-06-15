package com.anthonylldev.linkimbo.profile.application.service

import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.application.data.ProfileResponse

interface UserService {

    suspend fun loadProfile(userId: String): ProfileResponse?
    suspend fun getUserById(userId: String): User
    suspend fun updateUserById(user: User, userId: String)
}