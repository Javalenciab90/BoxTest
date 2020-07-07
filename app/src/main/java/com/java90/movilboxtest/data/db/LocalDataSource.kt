package com.java90.movilboxtest.data.db

import androidx.lifecycle.LiveData
import com.java90.movilboxtest.domain.models.Post

interface LocalDataSource {

    fun getFavoritesPosts() : LiveData<List<Post>>

    suspend fun insertPost(post: Post) : Long

    suspend fun deletePost(post: Post)

}