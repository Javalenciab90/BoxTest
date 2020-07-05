package com.java90.movilboxtest.repositories


import com.java90.movilboxtest.api.RetrofitInstance
import com.java90.movilboxtest.db.PostDatabase
import com.java90.movilboxtest.models.Post

class PostRepository(private val db: PostDatabase) {

    suspend fun getAllPosts() = RetrofitInstance.api.getPostsFromNetwork()

    fun getAllFavoritesPosts() = db.postDao().getAllPosts()

    suspend fun insertPost(post: Post) = db.postDao().insertPost(post)

    suspend fun isFavorite(id: Int) = db.postDao().isFavorite(id)

    suspend fun deletePost(post: Post) = db.postDao().deletePost(post)
}