package com.ravedya.cocktailrecipes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ravedya.core.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {
    val listCocktail = repository.getCocktails().asLiveData()
}