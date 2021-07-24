package com.ravedya.core.data.remote.network

import com.ravedya.core.data.remote.entity.CocktailResponse
import com.ravedya.core.data.remote.entity.Drink
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInstance {
    @GET("search.php?f=a")
    suspend fun getDrinksList(): CocktailResponse

    @GET("search.php")
    suspend fun searchDrink(@Query("s") query: String): CocktailResponse

    @GET("lookup.php")
    suspend fun getDrinkDetail(@Query("i") drinkId: Int): Drink

}