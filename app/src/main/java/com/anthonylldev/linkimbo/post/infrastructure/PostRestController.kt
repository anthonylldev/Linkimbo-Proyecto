package com.anthonylldev.linkimbo.post.infrastructure

import com.anthonylldev.linkimbo.post.application.data.PostRequest
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.post.domain.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostRestController {

    @POST("/post")
    suspend fun createPost(
        @Body post: PostRequest
    )

    @GET("/post")
    suspend fun getAllPosts(): List<PostResponse>

    @GET("/post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String
    ): PostResponse

}