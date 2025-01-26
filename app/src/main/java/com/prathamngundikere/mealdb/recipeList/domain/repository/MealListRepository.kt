package com.prathamngundikere.mealdb.recipeList.domain.repository

import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.recipeList.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealListRepository {
    suspend fun getMealList(category: String): Flow<Resource<List<Meal>>>
}