package com.ravedya.cocktailrecipes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravedya.cocktailrecipes.databinding.ItemCardListBinding
import com.ravedya.cocktailrecipes.utils.Helper
import com.ravedya.core.domain.model.CocktailModel

class CocktailAdapter : RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {

    private val listData = ArrayList<CocktailModel>()

    fun setData(newList: List<CocktailModel>?) {
        if (newList == null) return
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CocktailViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CocktailModel) {
            with(binding) {
                Helper.setImageWithGlide(itemView.context, data.drinkThumbnail, imgDrinkThumb)
                tvDrinkCategory.text = data.category
                tvDrinkName.text = data.drinkName

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = ItemCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CocktailModel)
    }


}