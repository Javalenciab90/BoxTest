package com.java90.movilboxtest.db
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    fun loadAll(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post)

    @Delete
    suspend fun delete(note: Post)

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()
}