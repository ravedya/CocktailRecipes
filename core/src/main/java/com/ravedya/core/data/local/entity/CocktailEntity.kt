package com.ravedya.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_cocktail")
data class CocktailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val drinkId: Int,

    @ColumnInfo(name = "name")
    val drinkName: String?,

    @ColumnInfo(name = "drinkThumbnail")
    val drinkThumbnail: String?,

    @ColumnInfo(name = "drinkImage")
    val drinkImage: String?,

    @ColumnInfo(name = "alcohol")
    val alcohol: String?,

    @ColumnInfo(name = "category")
    val category: String?,

    @ColumnInfo(name = "glass_type")
    val glassType: String?,

    @ColumnInfo(name = "instruction")
    val instruction: String?,

    /*Ingredients*/
    @ColumnInfo(name = "ingredient1")
    val ingredient1: String?,
    @ColumnInfo(name = "ingredient2")
    val ingredient2: String?,
    @ColumnInfo(name = "ingredient3")
    val ingredient3: String?,
    @ColumnInfo(name = "ingredient4")
    val ingredient4: String?,
    @ColumnInfo(name = "ingredient5")
    val ingredient5: String?,
    @ColumnInfo(name = "ingredient6")
    val ingredient6: String?,
    @ColumnInfo(name = "ingredient7")
    val ingredient7: String?,
    @ColumnInfo(name = "ingredient8")
    val ingredient8: String?,
    @ColumnInfo(name = "ingredient9")
    val ingredient9: String?,
    @ColumnInfo(name = "ingredient10")
    val ingredient10: String?,
    @ColumnInfo(name = "ingredient11")
    val ingredient11: String?,
    @ColumnInfo(name = "ingredient12")
    val ingredient12: String?,
    @ColumnInfo(name = "ingredient13")
    val ingredient13: String?,
    @ColumnInfo(name = "ingredient14")
    val ingredient14: String?,
    @ColumnInfo(name = "ingredient15")
    val ingredient15: String?,

    /*Measure of the Following Ingredients*/
    @ColumnInfo(name = "measure1")
    val measure1: String?,
    @ColumnInfo(name = "measure2")
    val measure2: String?,
    @ColumnInfo(name = "measure3")
    val measure3: String?,
    @ColumnInfo(name = "measure4")
    val measure4: String?,
    @ColumnInfo(name = "measure5")
    val measure5: String?,
    @ColumnInfo(name = "measure6")
    val measure6: String?,
    @ColumnInfo(name = "measure7")
    val measure7: String?,
    @ColumnInfo(name = "measure8")
    val measure8: String?,
    @ColumnInfo(name = "measure9")
    val measure9: String?,
    @ColumnInfo(name = "measure10")
    val measure10: String?,
    @ColumnInfo(name = "measure11")
    val measure11: String?,
    @ColumnInfo(name = "measure12")
    val measure12: String?,
    @ColumnInfo(name = "measure13")
    val measure13: String?,
    @ColumnInfo(name = "measure14")
    val measure14: String?,
    @ColumnInfo(name = "measure15")
    val measure15: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)
