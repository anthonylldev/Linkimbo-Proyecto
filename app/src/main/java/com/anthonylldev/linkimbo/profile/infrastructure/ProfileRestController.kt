package com.anthonylldev.linkimbo.profile.infrastructure

import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRestController {

    @GET("/user/profile/{userId}")
    suspend fun loadProfile(
        @Path("userId") userId: String
    ): ProfileResponse
}