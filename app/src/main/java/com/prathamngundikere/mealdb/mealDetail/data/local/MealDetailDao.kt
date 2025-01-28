package com.prathamngundikere.mealdb.mealDetail.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.prathamngundikere.mealdb.mealDetail.data.local.model.MealDetailEntity

@Dao
interface MealDetailDao {

    @Upsert
    suspend fun insertMealDetail(mealDetail: List<MealDetailEntity>)

    @Query("SELECT * FROM mealDetail WHERE idMeal = :idMeal")
    suspend fun getMealDetail(idMeal: String): List<MealDetailEntity>
}