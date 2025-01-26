package com.prathamngundikere.mealdb.recipeList.data.repository

import com.prathamngundikere.mealdb.core.database.AppDatabase
import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.recipeList.data.mappers.toMealList
import com.prathamngundikere.mealdb.recipeList.data.mappers.toMealListEntity
import com.prathamngundikere.mealdb.recipeList.data.remote.MealListApi
import com.prathamngundikere.mealdb.recipeList.domain.model.Meal
import com.prathamngundikere.mealdb.recipeList.domain.repository.MealListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealListRepositoryImpl @Inject constructor(
    private val mealListApi: MealListApi,
    private val appDatabase: AppDatabase
): MealListRepository  {
    override suspend fun getMealList(category: String): Flow<Resource<List<Meal>>> {
        return flow {
            emit(Resource.Loading(true))
            val localMealList = appDatabase.mealListDao.getAllMealList(category)
            val shouldLoadLocalMealList = localMealList.isNotEmpty()
            if (shouldLoadLocalMealList) {
                emit(Resource.Success(
                    data = localMealList.map {
                        it.toMealList()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val mealListFromApi = try {
                mealListApi.getRecipeList(category)
            } catch (e: Exception) {
                emit(Resource.Error("Error while loading"))
                return@flow
            }
            val mealListEntity = mealListFromApi.meals.let {
                it.map {
                    it.toMealListEntity(category)
                }
            }
            appDatabase.mealListDao.insertMealList(mealListEntity)
            emit(Resource.Success(
                mealListEntity.map {
                    it.toMealList()
                }
            ))
        }
    }
}