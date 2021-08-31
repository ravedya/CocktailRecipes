package com.ravedya.core.domain.repository

import android.util.Log
import com.ravedya.core.data.NetworkResourceBound
import com.ravedya.core.data.Resource
import com.ravedya.core.data.local.LocalDataSource
import com.ravedya.core.data.local.entity.CocktailEntity
import com.ravedya.core.data.remote.RemoteDataSource
import com.ravedya.core.data.remote.entity.Drink
import com.ravedya.core.data.remote.network.ApiResponse
import com.ravedya.core.domain.model.CocktailModel
import com.ravedya.core.domain.model.DatabaseMapper
import com.ravedya.core.domain.model.NetworkMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class RepositoryImpl
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkMapper: NetworkMapper,
    private val localDataSource: LocalDataSource,
    private val databaseMapper: DatabaseMapper
) : Repository {
    override fun getCocktails(): Flow<Resource<List<CocktailModel>>> {
        return object : NetworkResourceBound<List<CocktailModel?>, List<Drink>>() {
            override fun loadFromDB(): Flow<List<CocktailModel>> {
                return localDataSource.getListCocktails().map {
                    databaseMapper.fromEntityList(it)
                }
            }

            override fun shouldFetch(data: List<CocktailModel?>?): Boolean {
                return data?.isEmpty() == true || data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<List<Drink>>> {
                return remoteDataSource.getListDrinks()
            }

            override suspend fun saveCallResult(data: List<Drink>) {
                val drinkList = networkMapper.toEntityList(data)
                localDataSource.insertAllCocktails(drinkList)
            }


        }.asFlow() as Flow<Resource<List<CocktailModel>>>
    }

    override fun getFavoriteCocktails(): Flow<List<CocktailModel>> {
        return localDataSource.getFavCocktails().map {
            databaseMapper.fromEntityList(it)
        }
    }

    override fun getDetailCocktail(id: Int): Flow<Resource<CocktailModel>> {
        return object : NetworkResourceBound<CocktailModel, Drink>() {
            override fun loadFromDB(): Flow<CocktailModel?>? {
                return localDataSource.getCocktailById(id)?.map {
                    if(it == null){
                        return@map null
                    }else return@map databaseMapper.mapFromEntity(it)
                }
            }

            override fun shouldFetch(data: CocktailModel?): Boolean {
                return data != null && data.drinkName == null
            }

            override suspend fun createCall(): Flow<ApiResponse<Drink>> {
                return remoteDataSource.getDetailDrink(id)
            }

            override suspend fun saveCallResult(data: Drink) {
                val cocktailDetail = networkMapper.mapToEntity(data)
                localDataSource.insertCocktail(cocktailDetail)
            }

        }.asFlow() as Flow<Resource<CocktailModel>>
    }

    override fun setFavoriteCocktail(cocktailModel: CocktailModel) {
        val cocktailEntity = databaseMapper.mapToEntity(cocktailModel)
        cocktailEntity.isFavorite = !cocktailEntity.isFavorite
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.updateCocktail(cocktailEntity)
        }

    }

    override suspend fun searchCocktail(query: String): Resource<List<CocktailModel>> {
        return when (val response = remoteDataSource.searchDrink(query).first()) {
            is ApiResponse.Success -> {
                val cocktailEntity = networkMapper.toEntityList(response.data)
                Log.d("Cocktail Entity", cocktailEntity.toString())
                val cocktail = databaseMapper.fromEntityList(cocktailEntity)
                Log.d("Cocktail", cocktail.toString())
                Resource.Success(cocktail)
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorMessage, null)
            }
            is ApiResponse.Empty -> {
                Resource.Error(response.toString(), null)
            }
        }
    }

    override suspend fun insertCocktail(cocktailModel: CocktailModel) {
        val cocktailEntity = databaseMapper.mapToEntity(cocktailModel)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertCocktail(cocktailEntity)
        }
    }
}