package com.prathamngundikere.mealdb.recipeList.data.remote

import com.prathamngundikere.mealdb.recipeList.data.remote.response.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealListApi {

    @GET("api/json/v1/1/filter.php")
    suspend fun getRecipeList(
        @Query("c") category: String
    ): RecipeListDto
}