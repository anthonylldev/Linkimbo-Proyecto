package com.anthonylldev.linkimbo.post.application.service

import com.anthonylldev.linkimbo.post.application.data.PostRequest
import com.anthonylldev.linkimbo.post.application.data.PostResponse

interface PostService {

    suspend fun insertPost(request: PostRequest)

    suspend fun getAllPostSortByTimestamp(): List<PostResponse>

    suspend fun getPost(postId: String): PostResponse
}