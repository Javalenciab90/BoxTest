package com.java90.movilboxtest.domain.usecases

import androidx.lifecycle.LiveData
import com.java90.movilboxtest.data.db.LocalDataSource
import com.java90.movilboxtest.domain.models.Post
import javax.inject.Inject

class DataLocalUseCase @Inject constructor(private val localDataSource: LocalDataSource) {

    fun getLocalData() : LiveData<List<Post>> {
        return localDataSource.getFavoritesPosts()
    }

    suspend fun insert(post:Post) : Long = localDataSource.insertPost(post)

    suspend fun delete(post: Post) = localDataSource.deletePost(post)
}