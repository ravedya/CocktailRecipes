package com.ravedya.cocktailrecipes.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravedya.cocktailrecipes.databinding.FragmentExploreBinding
import com.ravedya.cocktailrecipes.ui.CocktailAdapter
import com.ravedya.core.data.Resource
import com.ravedya.core.domain.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val exploreViewModel: ExploreViewModel by viewModels()
    private var _binding: FragmentExploreBinding? = null
    private val adapter by lazy { CocktailAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(false)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    showLoading(true)
                    lifecycleScope.launch {
                        exploreViewModel.searchDrink(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        exploreViewModel.drinks.observe(viewLifecycleOwner,{
            when(it) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    adapter.setData(it.data)
                    showLoading(false)
                }
                is Resource.Error -> {
                    Toast.makeText(context, "Something doesn't right", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        })

        adapter.setOnItemClickCallback(object : CocktailAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CocktailModel?) {
                if (data != null) {
                    val id = data.drinkId
                    val action =
                        ExploreFragmentDirections.actionNavigationExploreToNavigationDetail(
                            id
                        )
                    lifecycleScope.launch {
                        exploreViewModel.insertCocktail(data)
                    }
                    findNavController().navigate(action)
                }
            }

        })

        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvExplore.layoutManager = LinearLayoutManager(context)
            rvExplore.adapter = adapter
            rvExplore.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbExplore.visibility = View.VISIBLE
        } else
            binding.pbExplore.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}