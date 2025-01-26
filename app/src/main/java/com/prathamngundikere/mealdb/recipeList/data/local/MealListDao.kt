package com.prathamngundikere.mealdb.recipeList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.prathamngundikere.mealdb.recipeList.data.local.model.MealListEntity

@Dao
interface MealListDao {

    @Upsert
    suspend fun insertMealList(mealList: List<MealListEntity>)

    @Query("SELECT * FROM meals WHERE category = :category")
    suspend fun getAllMealList(category: String): List<MealListEntity>
}