package com.ravedya.cocktailrecipes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravedya.cocktailrecipes.databinding.FragmentHomeBinding
import com.ravedya.cocktailrecipes.ui.CocktailAdapter
import com.ravedya.core.data.Resource
import com.ravedya.core.domain.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val adapter by lazy { CocktailAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.hide()

        homeViewModel.listCocktail.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        adapter.setData(it.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        })

        adapter.setOnItemClickCallback(object : CocktailAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CocktailModel?) {
                if (data != null) {
                    val id = data.drinkId
                    val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetail(
                        id
                    )
                    findNavController().navigate(action)
                }
            }
        })

        recyclerViewSetup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else
            binding.progressBar.visibility = View.INVISIBLE
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvHome.layoutManager = LinearLayoutManager(context)
            rvHome.adapter = adapter
            rvHome.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}