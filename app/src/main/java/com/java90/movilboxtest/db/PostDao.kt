package com.java90.movilboxtest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.java90.movilboxtest.models.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts() : LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Query("SELECT EXISTS (SELECT 1 FROM posts WHERE id=:id)")
    suspend fun isFavorite(id: Int) : Int

    @Delete
    suspend fun deletePost(post: Post)

}