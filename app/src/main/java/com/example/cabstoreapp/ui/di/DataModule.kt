package com.example.cabstoreapp.ui.di

import com.example.cabstoreapp.data.ProductDataRepository
import com.example.cabstoreapp.data.remote.ProductRemoteImpl
import com.example.cabstoreapp.data.source.ProductRemote
import com.example.cabstoreapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(data: ProductDataRepository): ProductRepository {
        return data
    }

    @Singleton
    @Provides
    fun provideDataSource(dataSourceRemote: ProductRemoteImpl): ProductRemote {
        return dataSourceRemote
    }
}
