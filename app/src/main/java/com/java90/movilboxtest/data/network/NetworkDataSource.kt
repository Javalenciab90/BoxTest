package com.java90.movilboxtest.data.network

import com.java90.movilboxtest.domain.models.Post
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getDataFromNetwork() : Response<List<Post>>
}