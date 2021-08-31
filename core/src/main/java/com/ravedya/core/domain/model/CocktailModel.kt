package com.ravedya.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailModel(

    val drinkId: Int=0,

    val drinkName: String?,

    val drinkThumbnail: String?,

    val drinkImage: String?,

    val alcohol: String?,

    val category: String?,

    val glassType: String?,

    val instruction: String?,

    /*Ingredients*/
    val ingredient1: String?,
    val ingredient2: String?,
    val ingredient3: String?,
    val ingredient4: String?,
    val ingredient5: String?,
    val ingredient6: String?,
    val ingredient7: String?,
    val ingredient8: String?,
    val ingredient9: String?,
    val ingredient10: String?,
    val ingredient11: String?,
    val ingredient12: String?,
    val ingredient13: String?,
    val ingredient14: String?,
    val ingredient15: String?,

    /*Measure of the Following Ingredients*/
    val measure1: String?,
    val measure2: String?,
    val measure3: String?,
    val measure4: String?,
    val measure5: String?,
    val measure6: String?,
    val measure7: String?,
    val measure8: String?,
    val measure9: String?,
    val measure10: String?,
    val measure11: String?,
    val measure12: String?,
    val measure13: String?,
    val measure14: String?,
    val measure15: String?,

    var isFavorite: Boolean
):Parcelable
