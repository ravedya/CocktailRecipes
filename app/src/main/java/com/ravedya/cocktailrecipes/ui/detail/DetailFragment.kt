package com.ravedya.cocktailrecipes.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ravedya.cocktailrecipes.databinding.FragmentDetailBinding
import com.ravedya.cocktailrecipes.utils.Helper
import com.ravedya.core.data.Resource
import com.ravedya.core.domain.model.CocktailModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val detailViewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.idDrink
        Log.d("ID", id.toString())

        if (id != 0) {
            detailViewModel.getDetailCocktail(id).observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        if (it != null) {
                            showLoading(false)
                            val data = it.data as CocktailModel
                            Log.d("DATA", data.toString())
                            setDataCocktail(data)
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showSnackBar("Something wrong happened")
                    }
                }
            })
        }
    }

    private fun setDataCocktail(data: CocktailModel) {
        binding.apply {
            tvDetailName.text = data.drinkName
            tvDetailCategory.text = data.category
            tvGlassType.text = data.glassType
            tvInstructionContent.text = data.instruction

            tvIng1.text = data.ingredient1
            tvIng2.text = data.ingredient2
            tvIng3.text = data.ingredient3
            tvIng4.text = data.ingredient4
            tvIng5.text = data.ingredient5
            tvIng6.text = data.ingredient6
            tvIng7.text = data.ingredient7
            tvIng8.text = data.ingredient8
            tvIng9.text = data.ingredient9
            tvIng10.text = data.ingredient10

            tvMsr1.text = data.measure1
            tvMsr2.text = data.measure2
            tvMsr3.text = data.measure3
            tvMsr4.text = data.measure4
            tvMsr5.text = data.measure5
            tvMsr6.text = data.measure6
            tvMsr7.text = data.measure7
            tvMsr8.text = data.measure8
            tvMsr9.text = data.measure9
            tvMsr10.text = data.measure10


            if (data.drinkImage == null) {
                Helper.setImageWithGlide(requireContext(), data.drinkThumbnail, imgTopBar)
            } else Helper.setImageWithGlide(requireContext(), data.drinkImage, imgTopBar)

            setFavButton(data.isFavorite)

            favoriteButton.setOnClickListener {
                setFavorite(data)
            }
        }
    }

    private fun setFavorite(cocktailModel: CocktailModel) {
        if (cocktailModel.isFavorite) {
            showSnackBar("${cocktailModel.drinkName} Removed from favorite")
        } else {
            showSnackBar("${cocktailModel.drinkName} Added to favorite")
        }
        detailViewModel.setFavoriteCocktail(cocktailModel)
    }

    private fun setFavButton(state: Boolean) {
        binding.favoriteButton.isChecked = state
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbDetail.visibility = View.VISIBLE
        } else
            binding.pbDetail.visibility = View.INVISIBLE
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
    }
}