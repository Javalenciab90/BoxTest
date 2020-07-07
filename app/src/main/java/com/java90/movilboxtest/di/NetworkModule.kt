package com.java90.movilboxtest.di

import android.util.Log
import com.java90.movilboxtest.framework.api.ApiService
import com.java90.movilboxtest.domain.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesBaseUrl() : String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(logging : HttpLoggingInterceptor) : OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun providesConverterFactory() : Converter.Factory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesRetrofitClient(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient) : Retrofit {

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun providesRestApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}