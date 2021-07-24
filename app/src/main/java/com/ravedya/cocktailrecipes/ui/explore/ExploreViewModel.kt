package com.ravedya.cocktailrecipes.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravedya.core.data.Resource
import com.ravedya.core.domain.model.CocktailModel
import com.ravedya.core.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    private var _drinks = MutableLiveData<Resource<List<CocktailModel>>>()
    val drinks: LiveData<Resource<List<CocktailModel>>> get() = _drinks

    suspend fun searchDrink(query: String){
        _drinks.value = repository.searchCocktail(query)
    }

    suspend fun insertCocktail(cocktail: CocktailModel) = repository.insertCocktail(cocktail)
    
}