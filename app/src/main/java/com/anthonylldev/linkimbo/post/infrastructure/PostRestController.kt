package com.anthonylldev.linkimbo.post.infrastructure

import com.anthonylldev.linkimbo.post.application.data.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostRestController {

    @POST("/post")
    suspend fun createPost(
        @Body postRequest: PostRequest
    )

    @POST("/post/{postId}/like")
    suspend fun like(
        @Path("postId") postId: String,
        @Body postLikeRequest: PostLikeRequest
    )

    @POST("/post/{postId}/comment")
    suspend fun comment(
        @Path("postId") postId: String,
        @Body postCommentRequest: PostCommentRequest
    )

    @GET("/post")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("/post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String
    ): PostResponse

    @GET("/post/{postId}/comment")
    suspend fun getAllComments(
        @Path("postId") postId: String
    ): List<PostCommentResponse>
}