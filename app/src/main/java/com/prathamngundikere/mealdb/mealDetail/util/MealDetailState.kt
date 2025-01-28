package com.prathamngundikere.mealdb.mealDetail.util

import com.prathamngundikere.mealdb.mealDetail.domain.model.MealDetail

data class MealDetailState(
    val isLoading: Boolean = false,
    val mealDetail: MealDetail = MealDetail(
        idMeal = "",
        strArea = "",
        strCategory = "",
        strInstructions = "",
        strMeal = "",
        strMealThumb = "",
        strTags = "",
        strYoutube = "",
    ),
    val error: String? = null
)
