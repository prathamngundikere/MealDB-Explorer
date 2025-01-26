package com.prathamngundikere.mealdb.recipeList.util

import com.prathamngundikere.mealdb.recipeList.domain.model.Meal

data class MealListState(
    val isLoading: Boolean = false,
    val mealList: List<Meal> = emptyList(),
    val error: String? = null
)
