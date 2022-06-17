package com.anthonylldev.linkimbo.post.application.service.impl

import com.anthonylldev.linkimbo.post.application.data.*
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.infrastructure.PostRestController

class PostServiceImpl(
    private val api: PostRestController
) : PostService {

    override suspend fun insertPost(request: PostRequest) {
        try {
            this.api.createPost(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPost(postId: String): PostResponse {
        try {
            return this.api.getPost(postId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun commentPost(postId: String, request: PostCommentRequest) {
        try {
            return this.api.comment(postId, request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllCommentsByPostId(postId: String): List<PostCommentResponse> {
        try {
            return this.api.getAllComments(postId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllPostSortByTimestamp(): List<PostResponse> {
        try {
            return this.api.getAllPosts()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun likePost(postId: String, request: PostLikeRequest) {
        try {
            this.api.like(postId, request)
        } catch (e: Exception) {
            throw e
        }
    }
}