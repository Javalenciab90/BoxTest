package com.java90.movilboxtest.framework.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.java90.movilboxtest.domain.models.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts() : LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post) : Long

    @Query("SELECT EXISTS (SELECT 1 FROM posts WHERE id=:id)")
    suspend fun isFavorite(id: Int) : Int

    @Delete
    suspend fun deletePost(post: Post)

}