package com.ravedya.core.di

import android.content.Context
import androidx.room.Room
import com.ravedya.core.data.local.database.CocktailDao
import com.ravedya.core.data.local.database.CocktailDatabase
import com.ravedya.core.domain.model.DatabaseMapper
import com.ravedya.core.domain.model.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideCocktailDao(cocktailDatabase: CocktailDatabase): CocktailDao{
        return cocktailDatabase.cocktailDao()
    }

    @Singleton
    @Provides
    fun provideCocktailDatabase(@ApplicationContext appContext: Context): CocktailDatabase {
        return Room.databaseBuilder(
            appContext,
            CocktailDatabase::class.java,
            "cocktail_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseMapper(): DatabaseMapper {
        return DatabaseMapper()
    }
}