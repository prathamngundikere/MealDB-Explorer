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