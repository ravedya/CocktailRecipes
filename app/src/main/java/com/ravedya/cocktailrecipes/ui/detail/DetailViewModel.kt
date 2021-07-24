package com.ravedya.cocktailrecipes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ravedya.core.domain.model.CocktailModel
import com.ravedya.core.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private val repository: Repository): ViewModel() {
    fun getDetailCocktail(id: Int) = repository.getDetailCocktail(id).asLiveData()
    fun setFavoriteCocktail(cocktail: CocktailModel) = repository.setFavoriteCocktail(cocktail)
}