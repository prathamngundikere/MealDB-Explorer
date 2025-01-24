package com.prathamngundikere.mealdb.category.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.prathamngundikere.mealdb.category.data.local.model.CategoryEntity

@Dao
interface CategoryDao {

    @Upsert
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<CategoryEntity>
}