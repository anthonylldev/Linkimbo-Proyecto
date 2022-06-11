package com.anthonylldev.linkimbo.profile.infrastructure

import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileRestController {

    @GET("/user/profile/{userId}")
    suspend fun loadProfile(
        @Path("userId") userId: String
    ): ProfileResponse

    @GET("/user")
    suspend fun getUser() : User

    @PUT("/user")
    suspend fun updateUser(
        @Body user: User
    )
}