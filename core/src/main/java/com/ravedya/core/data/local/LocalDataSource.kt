package com.ravedya.core.data.local

import com.ravedya.core.data.local.database.CocktailDao
import com.ravedya.core.data.local.entity.CocktailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource
@Inject
constructor(private val dao: CocktailDao){
    fun getListCocktails(): Flow<List<CocktailEntity>> = dao.getListCocktails()
    fun getFavCocktails(): Flow<List<CocktailEntity>> = dao.getFavCocktails()
    fun getCocktailById(id: Int): Flow<CocktailEntity>? = dao.getCocktailById(id)
    suspend fun insertAllCocktails(cocktails: List<CocktailEntity>) = dao.insertAllCocktails(cocktails)
    suspend fun insertCocktail(cocktail: CocktailEntity) = dao.insertCocktail(cocktail)
    fun updateCocktail(cocktail: CocktailEntity) = dao.updateCocktail(cocktail)
}