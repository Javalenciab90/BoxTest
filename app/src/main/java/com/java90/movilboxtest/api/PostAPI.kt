package com.java90.movilboxtest.api

import com.java90.movilboxtest.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {

    @GET("posts")
    suspend fun getPostsFromNetwork() : Response<List<Post>>

}