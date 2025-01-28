package com.prathamngundikere.mealdb.mealDetail.domain.repository

import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.mealDetail.domain.model.MealDetail
import kotlinx.coroutines.flow.Flow

interface MealDetailRepository {
    suspend fun getMealDetail(idMeal: String): Flow<Resource<List<MealDetail>>>
}