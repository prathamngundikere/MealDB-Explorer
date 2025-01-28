package com.prathamngundikere.mealdb.core.util

import kotlinx.serialization.Serializable

@Serializable
object CategoryScreen

@Serializable
data class CategoryRecipeList(
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

@Serializable
data class MealDetailScreen(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)