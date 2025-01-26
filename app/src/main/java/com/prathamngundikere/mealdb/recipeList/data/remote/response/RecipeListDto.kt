package com.prathamngundikere.mealdb.recipeList.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeListDto(
    @SerializedName("meals")
    val meals: List<MealListDto>
)