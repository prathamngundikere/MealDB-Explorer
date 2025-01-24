package com.prathamngundikere.mealdb.category.data.remote

import com.prathamngundikere.mealdb.category.data.remote.response.CategoryListDto
import retrofit2.http.GET

interface CategoryApi {

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategoryList(): CategoryListDto
}