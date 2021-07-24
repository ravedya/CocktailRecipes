package com.ravedya.core.data.local.database

import androidx.room.*
import com.ravedya.core.data.local.entity.CocktailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Query("SELECT * FROM table_cocktail")
    fun getListCocktails(): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM table_cocktail WHERE isFavorite = 1")
    fun getFavCocktails(): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM table_cocktail WHERE id = :id")
    fun getCocktailById(id: Int): Flow<CocktailEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCocktails(cocktails: List<CocktailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail: CocktailEntity)

    @Update
    fun updateCocktail(cocktail: CocktailEntity)
}