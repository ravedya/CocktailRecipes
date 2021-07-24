package com.ravedya.core.domain.model

import com.ravedya.core.data.local.entity.CocktailEntity
import com.ravedya.core.utils.Mapper

class DatabaseMapper:Mapper<CocktailEntity, CocktailModel> {
    override fun mapFromEntity(entity: CocktailEntity?): CocktailModel {
        return CocktailModel(
            drinkId = entity!!.drinkId,
            drinkName = entity?.drinkName,
            drinkThumbnail = entity?.drinkThumbnail,
            drinkImage = entity?.drinkImage,
            alcohol = entity?.alcohol,
            category = entity?.category,
            glassType = entity?.glassType,
            instruction = entity?.instruction,
            ingredient1 = entity?.ingredient1,
            ingredient2 = entity?.ingredient2,
            ingredient3 = entity?.ingredient3,
            ingredient4 = entity?.ingredient4,
            ingredient5 = entity?.ingredient5,
            ingredient6 = entity?.ingredient6,
            ingredient7 = entity?.ingredient7,
            ingredient8 = entity?.ingredient8,
            ingredient9 = entity?.ingredient9,
            ingredient10 = entity?.ingredient10,
            ingredient11 = entity?.ingredient11,
            ingredient12 = entity?.ingredient12,
            ingredient13 = entity?.ingredient13,
            ingredient14 = entity?.ingredient14,
            ingredient15 = entity?.ingredient15,
            measure1 = entity?.measure1,
            measure2 = entity?.measure2,
            measure3 = entity?.measure3,
            measure4 = entity?.measure4,
            measure5 = entity?.measure5,
            measure6 = entity?.measure6,
            measure7 = entity?.measure7,
            measure8 = entity?.measure8,
            measure9 = entity?.measure9,
            measure10 = entity?.measure10,
            measure11 = entity?.measure11,
            measure12 = entity?.measure12,
            measure13 = entity?.measure13,
            measure14 = entity?.measure14,
            measure15 = entity?.measure15,
            isFavorite = entity?.isFavorite
        )
    }

    override fun mapToEntity(domainModel: CocktailModel?): CocktailEntity {
        return CocktailEntity(
            drinkId = domainModel!!.drinkId,
            drinkName = domainModel.drinkName,
            drinkThumbnail = domainModel.drinkThumbnail,
            drinkImage = domainModel.drinkImage,
            alcohol = domainModel.alcohol,
            category = domainModel.category,
            glassType = domainModel.glassType,
            instruction = domainModel.instruction,
            ingredient1 = domainModel.ingredient1,
            ingredient2 = domainModel.ingredient2,
            ingredient3 = domainModel.ingredient3,
            ingredient4 = domainModel.ingredient4,
            ingredient5 = domainModel.ingredient5,
            ingredient6 = domainModel.ingredient6,
            ingredient7 = domainModel.ingredient7,
            ingredient8 = domainModel.ingredient8,
            ingredient9 = domainModel.ingredient9,
            ingredient10 = domainModel.ingredient10,
            ingredient11 = domainModel.ingredient11,
            ingredient12 = domainModel.ingredient12,
            ingredient13 = domainModel.ingredient13,
            ingredient14 = domainModel.ingredient14,
            ingredient15 = domainModel.ingredient15,
            measure1 = domainModel.measure1,
            measure2 = domainModel.measure2,
            measure3 = domainModel.measure3,
            measure4 = domainModel.measure4,
            measure5 = domainModel.measure5,
            measure6 = domainModel.measure6,
            measure7 = domainModel.measure7,
            measure8 = domainModel.measure8,
            measure9 = domainModel.measure9,
            measure10 = domainModel.measure10,
            measure11 = domainModel.measure11,
            measure12 = domainModel.measure12,
            measure13 = domainModel.measure13,
            measure14 = domainModel.measure14,
            measure15 = domainModel.measure15,
            isFavorite = domainModel.isFavorite
        )
    }

    fun fromEntityList(initial: List<CocktailEntity>): List<CocktailModel>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<CocktailModel>): List<CocktailEntity>{
        return initial.map { mapToEntity(it) }
    }
}