package com.prathamngundikere.mealdb.recipeList.data.mappers

import com.prathamngundikere.mealdb.recipeList.data.local.model.MealListEntity
import com.prathamngundikere.mealdb.recipeList.data.remote.response.MealListDto
import com.prathamngundikere.mealdb.recipeList.domain.model.Meal

fun MealListDto.toMealListEntity(category: String): MealListEntity {
    return MealListEntity(
        idMeal = idMeal ?: "",
        strMeal = strMeal ?: "",
        strMealThumb = strMealThumb ?: "",
        category = category
    )
}

fun MealListEntity.toMealList(): Meal {
    return Meal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}