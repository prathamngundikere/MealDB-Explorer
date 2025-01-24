package com.prathamngundikere.mealdb.category.util

import com.prathamngundikere.mealdb.category.domain.model.Category

data class CategoryState(
    val isLoading: Boolean = false,
    val categoryList: List<Category> = emptyList()
)
