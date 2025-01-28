package com.prathamngundikere.mealdb.core.di

import android.app.Application
import androidx.room.Room
import com.prathamngundikere.mealdb.category.data.remote.CategoryApi
import com.prathamngundikere.mealdb.category.data.repository.CategoryListRepositoryImpl
import com.prathamngundikere.mealdb.category.domain.repository.CategoryListRepository
import com.prathamngundikere.mealdb.core.database.AppDatabase
import com.prathamngundikere.mealdb.mealDetail.data.remote.MealDetailApi
import com.prathamngundikere.mealdb.mealDetail.data.repository.MealDetailRepositoryImpl
import com.prathamngundikere.mealdb.mealDetail.domain.repository.MealDetailRepository
import com.prathamngundikere.mealdb.recipeList.data.remote.MealListApi
import com.prathamngundikere.mealdb.recipeList.data.repository.MealListRepositoryImpl
import com.prathamngundikere.mealdb.recipeList.domain.repository.MealListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun providesCategoryApi(): CategoryApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/")
            .client(client)
            .build()
            .create(CategoryApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRecipeListApi(): MealListApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/")
            .client(client)
            .build()
            .create(MealListApi::class.java)
    }

    @Singleton
    @Provides
    fun providesMealDetailApi(): MealDetailApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/")
            .client(client)
            .build()
            .create(MealDetailApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java,
            name = "meal.db"
        ).build()
    }

    @Provides
    fun provideCategoryRepo(
        categoryApi: CategoryApi,
        appDatabase: AppDatabase
    ): CategoryListRepository {
        return CategoryListRepositoryImpl(categoryApi, appDatabase)
    }

    @Provides
    fun provideMealListRepo(
        mealListApi: MealListApi,
        appDatabase: AppDatabase
    ): MealListRepository {
        return MealListRepositoryImpl(mealListApi, appDatabase)
    }

    @Provides
    fun provideMealDetailRepo(
        mealDetailApi: MealDetailApi,
        appDatabase: AppDatabase
    ): MealDetailRepository {
        return MealDetailRepositoryImpl(mealDetailApi, appDatabase)
    }
}