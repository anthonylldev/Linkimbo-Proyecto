package com.anthonylldev.linkimbo.post.application.service.impl

import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.model.Post
import com.anthonylldev.linkimbo.post.infrastructure.PostRestController

class PostServiceImpl(
    private val api: PostRestController
) : PostService {

    override suspend fun insertPost(request: Post) {
        try {
            this.api.createPost(request)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPost(postId: String): Post {
        try {
            return this.api.getPost(postId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllPostSortByTimestamp(): List<Post> {
        try {
            return this.api.getAllPosts()
        } catch (e: Exception) {
            throw e
        }
    }
}