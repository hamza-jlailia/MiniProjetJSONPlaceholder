package com.example.miniprojet.repository

import com.example.miniprojet.model.Post
import com.example.miniprojet.remote.JsonPlaceholderApi
import com.example.miniprojet.remote.RetrofitClient

class PostRepository(private val api: JsonPlaceholderApi = RetrofitClient.api) {
    suspend fun list() = api.getPosts()
    suspend fun details(id: Int) = api.getPost(id)
    suspend fun comments(id: Int) = api.getComments(id)
    suspend fun create(userId: Int, title: String, body: String) =
        api.createPost(Post(userId = userId, title = title, body = body))
    suspend fun patch(id: Int, patch: Map<String, Any>) = api.updatePost(id, patch)
    suspend fun remove(id: Int) = api.deletePost(id).isSuccessful
}