package com.java90.movilboxtest.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.db.PostDao
import com.java90.movilboxtest.db.PostDatabase

class RoomRepository(application: Application) {

    private val postsDao: PostDao = PostDatabase.getDatabase(application).postDao()

    private val postListFavorites: LiveData<List<Post>>

    init {
        postListFavorites = postsDao.loadAll()
    }

    fun loadAllPostFromDatabase() : LiveData<List<Post>> {
        return postListFavorites
    }

    suspend fun insertPostInDatabase(post: Post){
        postsDao.insert(post)
    }

    suspend fun deletePostFromDatabase(post: Post) {
        postsDao.delete(post)
    }

    suspend fun deleteAllPostsFromDatabase(){
        postsDao.deleteAll()
    }
}