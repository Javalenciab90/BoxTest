package com.java90.movilboxtest.di

import com.java90.movilboxtest.data.db.LocalDataSource
import com.java90.movilboxtest.domain.usecases.DataLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalUseCaseModule {

    @Singleton
    @Provides
    fun providesLocalUseCase (localDataSource: LocalDataSource) : DataLocalUseCase {
        return DataLocalUseCase(localDataSource)
    }
}