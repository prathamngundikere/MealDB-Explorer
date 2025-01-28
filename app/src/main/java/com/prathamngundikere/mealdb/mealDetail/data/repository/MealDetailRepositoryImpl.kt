package com.prathamngundikere.mealdb.mealDetail.data.repository

import com.prathamngundikere.mealdb.core.database.AppDatabase
import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.mealDetail.data.mappers.toMealDetail
import com.prathamngundikere.mealdb.mealDetail.data.mappers.toMealsDetailEntity
import com.prathamngundikere.mealdb.mealDetail.data.remote.MealDetailApi
import com.prathamngundikere.mealdb.mealDetail.domain.model.MealDetail
import com.prathamngundikere.mealdb.mealDetail.domain.repository.MealDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealDetailRepositoryImpl @Inject constructor(
    private val mealDetailApi: MealDetailApi,
    private val appDatabase: AppDatabase
): MealDetailRepository {
    override suspend fun getMealDetail(idMeal: String): Flow<Resource<List<MealDetail>>> {
        return flow {
            emit(Resource.Loading(true))
            val localMealDetail = appDatabase.mealDetailDao.getMealDetail(idMeal)
            val shouldLoadLocalMealDetail = localMealDetail.isNotEmpty()
            if (shouldLoadLocalMealDetail) {
                emit(Resource.Success(
                    data = localMealDetail.map {
                        it.toMealDetail()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val mealDetailFromApi = try {
                mealDetailApi.getMealDetail(idMeal)
            } catch (e: Exception) {
                emit(Resource.Error("Error while Loading"))
                return@flow
            }
            val mealDetailEntity = mealDetailFromApi.meals.let {
                it.map {
                    it.toMealsDetailEntity()
                }
            }
            appDatabase.mealDetailDao.insertMealDetail(mealDetailEntity)
            emit(Resource.Success(
                mealDetailEntity.map {
                    it.toMealDetail()
                }
            ))
            emit(Resource.Loading(false))
        }
    }
}