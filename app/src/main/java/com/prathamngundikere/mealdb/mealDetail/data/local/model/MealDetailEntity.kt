package com.prathamngundikere.mealdb.mealDetail.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mealDetail")
data class MealDetailEntity(
    @PrimaryKey
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String,
)
