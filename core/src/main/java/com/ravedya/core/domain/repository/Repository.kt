package com.ravedya.core.domain.repository

import com.ravedya.core.data.Resource
import com.ravedya.core.domain.model.CocktailModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCocktails(): Flow<Resource<List<CocktailModel>>>
    fun getFavoriteCocktails(): Flow<List<CocktailModel>>
    fun getDetailCocktail(id: Int): Flow<Resource<CocktailModel>>
    fun setFavoriteCocktail(cocktailModel: CocktailModel)
    suspend fun searchCocktail(query: String): Resource<List<CocktailModel>>
    suspend fun insertCocktail(cocktailModel: CocktailModel)
}