package com.java90.movilboxtest.repositories

import android.app.Application
import android.util.Log
import com.java90.movilboxtest.network.NetworkApi
import com.java90.movilboxtest.vo.Resource

class NetworkRepository(application: Application) {

    private val networkApi: NetworkApi = NetworkApi()
    private val roomRepository: RoomRepository = RoomRepository(application)

    suspend fun fetchDataInDatabase() : Resource<String>{
            Log.d("TAG", "Fetch Data: " + Thread.currentThread().name)
            val response = networkApi.getPostsFromNetwork().body()!!.toList()
            while(roomRepository.databaseIsEmpty() == 0) {
                response.forEach { post ->
                    roomRepository.insertPostInDatabase(post)
                }
            }
        return Resource.Success("Success")
    }

}