package com.prathamngundikere.mealdb.mealDetail.data.mappers

import com.prathamngundikere.mealdb.mealDetail.data.local.model.MealDetailEntity
import com.prathamngundikere.mealdb.mealDetail.data.remote.response.MealDto
import com.prathamngundikere.mealdb.mealDetail.domain.model.MealDetail

fun MealDto.toMealsDetailEntity(): MealDetailEntity {
    return MealDetailEntity(
        idMeal = idMeal ?: "",
        strArea = strArea ?: "",
        strCategory = strCategory ?: "",
        strInstructions = strInstructions ?: "",
        strMeal = strMeal ?: "",
        strMealThumb = strMealThumb ?: "",
        strTags = strTags ?: "",
        strYoutube = strYoutube ?: ""
    )
}

fun MealDetailEntity.toMealDetail(): MealDetail {
    return MealDetail(
        idMeal = idMeal,
        strArea = strArea,
        strCategory = strCategory,
        strInstructions = strInstructions,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        strTags = strTags,
        strYoutube = strYoutube
    )
}