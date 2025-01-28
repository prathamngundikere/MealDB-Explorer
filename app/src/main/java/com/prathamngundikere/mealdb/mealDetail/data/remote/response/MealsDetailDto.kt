package com.prathamngundikere.mealdb.mealDetail.data.remote.response

import com.google.gson.annotations.SerializedName

data class MealsDetailDto(
    @SerializedName("meals")
    val meals: List<MealDto>
)