package com.prathamngundikere.mealdb.category.data.mappers

import com.prathamngundikere.mealdb.category.data.local.model.CategoryEntity
import com.prathamngundikere.mealdb.category.data.remote.response.CategoryDto
import com.prathamngundikere.mealdb.category.domain.model.Category

fun CategoryDto.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        idCategory = idCategory ?: "",
        strCategory = strCategory ?: "",
        strCategoryDescription = strCategoryDescription ?: "",
        strCategoryThumb = strCategoryThumb ?: ""
    )
}

fun CategoryEntity.toCategory(): Category {
    return Category(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryDescription = strCategoryDescription,
        strCategoryThumb = strCategoryThumb
    )
}