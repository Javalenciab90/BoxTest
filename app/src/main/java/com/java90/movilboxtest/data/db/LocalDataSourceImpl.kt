package com.java90.movilboxtest.data.db

import androidx.lifecycle.LiveData
import com.java90.movilboxtest.domain.models.Post
import com.java90.movilboxtest.framework.db.PostDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val postDao: PostDao) : LocalDataSource {

    override fun getFavoritesPosts(): LiveData<List<Post>> {
        return postDao.getAllPosts()
    }

    override suspend fun insertPost(post: Post) : Long = postDao.insertPost(post)

    override suspend fun deletePost(post: Post) = postDao.deletePost(post)

}