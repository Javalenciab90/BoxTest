package com.java90.movilboxtest.domain.usecases

import com.java90.movilboxtest.data.network.NetworkDataSource
import com.java90.movilboxtest.domain.models.Post
import retrofit2.Response
import javax.inject.Inject

class DataNetworkUseCase @Inject constructor (
    private val networkDataSource: NetworkDataSource) {

    suspend fun getDataNetwork() : Response<List<Post>> {
        return networkDataSource.getDataFromNetwork()
    }
}