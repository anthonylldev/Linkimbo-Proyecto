package com.anthonylldev.linkimbo.profile.application.service

import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse

interface ProfileService {

    suspend fun loadProfile(userId: String): ProfileResponse?

    suspend fun getUser(): User
    suspend fun updateUser(user: User)
}