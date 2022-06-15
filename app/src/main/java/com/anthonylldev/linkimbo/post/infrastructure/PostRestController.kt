package com.anthonylldev.linkimbo.post.infrastructure

import com.anthonylldev.linkimbo.post.domain.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostRestController {

    @POST("/post")
    suspend fun createPost(
        @Body post: Post
    )

    @GET("/post")
    suspend fun getAllPosts(): List<Post>

    @GET("/post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String
    ): Post

}