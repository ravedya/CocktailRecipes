package com.ravedya.core.di

import com.ravedya.core.data.local.LocalDataSource
import com.ravedya.core.data.local.database.CocktailDao
import com.ravedya.core.data.remote.RemoteDataSource
import com.ravedya.core.data.remote.network.ApiInstance
import com.ravedya.core.domain.model.DatabaseMapper
import com.ravedya.core.domain.model.NetworkMapper
import com.ravedya.core.domain.repository.Repository
import com.ravedya.core.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        networkMapper: NetworkMapper,
        localDataSource: LocalDataSource,
        databaseMapper: DatabaseMapper
    ): Repository {
        return RepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            networkMapper = networkMapper,
            databaseMapper = databaseMapper
        )
    }

    @Singleton
    @Provides
    fun provideRemoteData(apiInstance: ApiInstance): RemoteDataSource {
        return RemoteDataSource(apiInstance)
    }

    @Singleton
    @Provides
    fun provideLocalData(dao: CocktailDao): LocalDataSource {
        return LocalDataSource(dao)
    }
}