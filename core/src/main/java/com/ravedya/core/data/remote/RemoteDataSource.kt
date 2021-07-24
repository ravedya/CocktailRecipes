package com.ravedya.core.data.remote

import android.util.Log
import com.ravedya.core.data.remote.entity.Drink
import com.ravedya.core.data.remote.network.ApiInstance
import com.ravedya.core.data.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource
@Inject
constructor(private val apiInstance: ApiInstance) {
    suspend fun getListDrinks(): Flow<ApiResponse<List<Drink>>> {
        return flow {
            try {
                val response = apiInstance.getDrinksList()
                val drink = response.drinks
                if (drink.isNotEmpty()) {
                    emit(ApiResponse.Success(drink))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchDrink(query: String): Flow<ApiResponse<List<Drink>>> {
        return flow {
            try {
                val response = apiInstance.searchDrink(query)
                val drink = response.drinks
                if (drink.isNotEmpty()) {
                    emit(ApiResponse.Success(drink))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailDrink(id: Int): Flow<ApiResponse<Drink>> {
        return flow {
            try {
                val response = apiInstance.getDrinkDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}