package com.prathamngundikere.mealdb.category.domain.repository

import com.prathamngundikere.mealdb.category.domain.model.Category
import com.prathamngundikere.mealdb.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryListRepository {
    suspend fun getCategoryList(): Flow<Resource<List<Category>>>
}