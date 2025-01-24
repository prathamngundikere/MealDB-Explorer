package com.prathamngundikere.mealdb.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prathamngundikere.mealdb.category.data.local.CategoryDao
import com.prathamngundikere.mealdb.category.data.local.model.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val categoryDao: CategoryDao
}