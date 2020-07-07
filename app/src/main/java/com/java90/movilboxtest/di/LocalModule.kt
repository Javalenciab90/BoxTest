package com.java90.movilboxtest.di

import android.content.Context
import androidx.room.Room
import com.java90.movilboxtest.domain.util.Constants.Companion.POSTS_DATABASE_NAME
import com.java90.movilboxtest.framework.db.PostDao
import com.java90.movilboxtest.framework.db.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providePostDatabase(@ApplicationContext app: Context) : PostDatabase {
        return Room.databaseBuilder(
            app,
            PostDatabase::class.java,
            POSTS_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostDatabase) : PostDao {
        return db.postDao()
    }
}