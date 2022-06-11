package com.anthonylldev.linkimbo.profile.application.service

import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse

interface ProfileService {

    suspend fun loadProfile(userId: String): ProfileResponse?
}