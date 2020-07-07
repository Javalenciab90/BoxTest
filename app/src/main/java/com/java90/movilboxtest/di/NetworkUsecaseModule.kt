package com.java90.movilboxtest.di

import com.java90.movilboxtest.data.network.NetworkDataSource
import com.java90.movilboxtest.domain.usecases.DataNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkUseCaseModule {

    @Singleton
    @Provides
    fun providesNetworkUseCase(networkDataSource: NetworkDataSource) : DataNetworkUseCase {
        return DataNetworkUseCase(networkDataSource)
    }
}