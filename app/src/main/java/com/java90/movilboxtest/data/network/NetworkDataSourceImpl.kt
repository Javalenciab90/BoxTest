package com.java90.movilboxtest.data.network

import android.util.Log
import com.java90.movilboxtest.domain.models.Post
import com.java90.movilboxtest.framework.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService) : NetworkDataSource {

    override suspend fun getDataFromNetwork(): Response<List<Post>> {
        return apiService.getDataFromNetwork()
    }
}