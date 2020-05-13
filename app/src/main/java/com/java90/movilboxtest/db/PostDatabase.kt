package com.java90.movilboxtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.java90.movilboxtest.models.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao() : PostDao

    companion object {

        @Volatile
        private var instance : PostDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PostDatabase::class.java,
            "post_db.db"
        ).build()
    }
}