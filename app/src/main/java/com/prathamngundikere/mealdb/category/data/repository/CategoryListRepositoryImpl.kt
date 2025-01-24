package com.prathamngundikere.mealdb.category.data.repository

import com.prathamngundikere.mealdb.category.data.mappers.toCategory
import com.prathamngundikere.mealdb.category.data.mappers.toCategoryEntity
import com.prathamngundikere.mealdb.category.data.remote.CategoryApi
import com.prathamngundikere.mealdb.category.domain.model.Category
import com.prathamngundikere.mealdb.category.domain.repository.CategoryListRepository
import com.prathamngundikere.mealdb.core.database.AppDatabase
import com.prathamngundikere.mealdb.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryListRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val appDatabase: AppDatabase
): CategoryListRepository {

    override suspend fun getCategoryList(): Flow<Resource<List<Category>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCategoryList = appDatabase.categoryDao.getAllCategories()
            val shouldLoadLocalCategory = localCategoryList.isNotEmpty()
            if (shouldLoadLocalCategory) {
                emit(Resource.Success(
                    data = localCategoryList.map {
                        it.toCategory()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val categoryListFromApi = try {
                categoryApi.getCategoryList()
            } catch (e: Exception) {
                emit(Resource.Error("Error while loading"))
                return@flow
            }
            val categoryEntities = categoryListFromApi.categories.let {
                it.map {
                    it.toCategoryEntity()
                }
            }
            appDatabase.categoryDao.insertCategories(categoryEntities)
            emit(Resource.Success(
                categoryEntities.map {
                    it.toCategory()
                }
            ))
            emit(Resource.Loading(false))
        }
    }
}