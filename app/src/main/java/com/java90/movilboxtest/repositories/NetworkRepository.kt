package com.java90.movilboxtest.repositories

import android.app.Application
import android.util.Log
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.network.NetworkApi
import com.java90.movilboxtest.vo.Resource
import retrofit2.Response

class NetworkRepository(application: Application) {

    private val networkApi: NetworkApi = NetworkApi()

    suspend fun fetchDataInDatabase() : Resource<Response<List<Post>>>{
        Log.d("TAG", "Fetch Data: " + Thread.currentThread().name)
        return Resource.Success(networkApi.getPostsFromNetwork())
    }
}