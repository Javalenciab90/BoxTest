package com.java90.movilboxtest.di

import com.java90.movilboxtest.data.network.NetworkDataSource
import com.java90.movilboxtest.data.network.NetworkDataSourceImpl
import com.java90.movilboxtest.domain.usecases.DataNetworkUseCase
import com.java90.movilboxtest.framework.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkDataSourceModule {

    @Singleton
    @Provides
    fun providesNetworkDataSource(apiService: ApiService) : NetworkDataSource {
       return NetworkDataSourceImpl(apiService)
    }
}