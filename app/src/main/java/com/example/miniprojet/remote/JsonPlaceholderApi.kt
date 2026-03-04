package com.example.miniprojet.remote

import com.example.miniprojet.model.Comment
import com.example.miniprojet.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonPlaceholderApi {
    @GET("posts") suspend fun getPosts(): List<Post>
    @GET("posts/{id}") suspend fun getPost(@Path("id") id: Int): Post
    @GET("posts/{id}/comments") suspend fun getComments(@Path("id") id: Int): List<Comment>
    @POST("posts") suspend fun createPost(@Body post: Post): Post
    @PATCH("posts/{id}") suspend fun updatePost(@Path("id") id: Int, @Body patch: Map<String, Any>): Post
    @DELETE("posts/{id}") suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}