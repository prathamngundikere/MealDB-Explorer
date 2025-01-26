package com.prathamngundikere.mealdb.recipeList.data.remote.response

import com.google.gson.annotations.SerializedName

data class MealListDto(
    @SerializedName("idMeal")
    val idMeal: String?,
    @SerializedName("strMeal")
    val strMeal: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?,
)