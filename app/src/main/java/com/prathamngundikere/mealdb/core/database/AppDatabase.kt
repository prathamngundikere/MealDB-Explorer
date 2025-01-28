package com.prathamngundikere.mealdb.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prathamngundikere.mealdb.category.data.local.CategoryDao
import com.prathamngundikere.mealdb.category.data.local.model.CategoryEntity
import com.prathamngundikere.mealdb.mealDetail.data.local.MealDetailDao
import com.prathamngundikere.mealdb.mealDetail.data.local.model.MealDetailEntity
import com.prathamngundikere.mealdb.recipeList.data.local.MealListDao
import com.prathamngundikere.mealdb.recipeList.data.local.model.MealListEntity

@Database(
    entities = [CategoryEntity::class, MealListEntity::class, MealDetailEntity::class],
    version = 3
)
abstract class AppDatabase: RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val mealListDao: MealListDao
    abstract val mealDetailDao: MealDetailDao
}