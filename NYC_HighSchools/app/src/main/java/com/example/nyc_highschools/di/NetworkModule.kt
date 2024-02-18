package com.example.nyc_highschools.di

import com.example.nyc_highschools.data.network.NYCApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideApiService(): NYCApiService {
        return NYCApiService.create()
    }
}
