package com.prathamngundikere.mealdb.category.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryListDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>
)