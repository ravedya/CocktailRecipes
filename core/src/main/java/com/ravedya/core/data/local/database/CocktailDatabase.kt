package com.ravedya.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravedya.core.data.local.entity.CocktailEntity

@Database(entities = [CocktailEntity::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}