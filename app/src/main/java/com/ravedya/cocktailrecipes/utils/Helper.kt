package com.ravedya.cocktailrecipes.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object Helper {

    fun setImageWithGlide(context: Context, imagePath: String?, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).transform(RoundedCorners(27)).centerCrop()
            .into(imageView)
    }
}