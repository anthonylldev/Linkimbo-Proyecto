package com.anthonylldev.linkimbo.post.application.service

import com.anthonylldev.linkimbo.post.application.data.*

interface PostService {

    suspend fun insertPost(request: PostRequest)

    suspend fun getAllPostSortByTimestamp(): List<PostResponse>

    suspend fun getPost(postId: String): PostResponse

    suspend fun likePost(postId: String, request: LikeRequest)

    suspend fun commentPost(postId: String, request: PostCommentRequest)

    suspend fun getAllCommentsByPostId(postId: String): List<PostCommentResponse>

    suspend fun likePostComment(postId: String, commentId: String, request: LikeRequest)
}