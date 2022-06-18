package com.anthonylldev.linkimbo.profile.infrastructure

import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.application.data.ProfilePostResponse
import com.anthonylldev.linkimbo.profile.application.data.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserRestController {

    @GET("/user/profile/{userId}")
    suspend fun loadProfile(
        @Path("userId") userId: String
    ): ProfileResponse

    @GET("/user/profile/{userId}/posts")
    suspend fun loadPosts(
        @Path("userId") userId: String
    ): List<ProfilePostResponse>

    @GET("/user/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: String
    ): User

    @PUT("/user/{userId}")
    suspend fun updateUserById(
        @Body user: User,
        @Path("userId") userId: String
    )
}