package com.anthonylldev.linkimbo.post.application.service

import com.anthonylldev.linkimbo.post.domain.model.Post

interface PostService {

    suspend fun insertPost(request: Post)

    suspend fun getAllPostSortByTimestamp(): List<Post>

    suspend fun getPost(postId: String): Post
}