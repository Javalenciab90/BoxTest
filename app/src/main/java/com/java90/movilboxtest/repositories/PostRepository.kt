package com.java90.movilboxtest.repositories


import com.java90.movilboxtest.api.RetrofitInstance

class PostRepository {

    suspend fun getAllPosts() = RetrofitInstance.api.getPostsFromNetwork()
}