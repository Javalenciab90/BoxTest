package com.java90.movilboxtest.di

import com.java90.movilboxtest.data.db.LocalDataSource
import com.java90.movilboxtest.data.db.LocalDataSourceImpl
import com.java90.movilboxtest.framework.db.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(db: PostDao) : LocalDataSource {
        return LocalDataSourceImpl(db)
    }
}