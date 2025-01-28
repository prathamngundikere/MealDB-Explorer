package com.prathamngundikere.mealdb.mealDetail.data.remote

import com.prathamngundikere.mealdb.mealDetail.data.remote.response.MealsDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailApi {

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetail(
        @Query("i") idMeal: String
    ): MealsDetailDto
}