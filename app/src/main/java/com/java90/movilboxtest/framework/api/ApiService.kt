package com.java90.movilboxtest.framework.api

import com.java90.movilboxtest.domain.models.Post
import com.java90.movilboxtest.domain.util.Constants.Companion.URL_POSTS
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(URL_POSTS)
    suspend fun getDataFromNetwork() : Response<List<Post>>
}