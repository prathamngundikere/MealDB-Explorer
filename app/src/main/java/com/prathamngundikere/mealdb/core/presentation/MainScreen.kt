package com.prathamngundikere.mealdb.core.presentation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.prathamngundikere.mealdb.category.presentation.CategoryListViewModel
import com.prathamngundikere.mealdb.category.presentation.CategoryScreen
import com.prathamngundikere.mealdb.core.util.CategoryRecipeList
import com.prathamngundikere.mealdb.core.util.CategoryScreen
import com.prathamngundikere.mealdb.recipeList.presentation.CategoryRecipeListScreen
import com.prathamngundikere.mealdb.recipeList.presentation.MealListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(){

    val navController = rememberNavController()

    val categoryListViewModel = hiltViewModel<CategoryListViewModel>()
    val categoryState = categoryListViewModel.categoryState.collectAsState().value

    val  mealListViewModel = hiltViewModel<MealListViewModel>()
    val mealListState = mealListViewModel.mealListState.collectAsState().value

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = CategoryScreen
        ) {
            composable<CategoryScreen> {
                CategoryScreen(
                    animatedVisibilityScope = this@composable,
                    categoryState = categoryState,
                    navController = navController
                )
            }
            composable<CategoryRecipeList> {
                val args = it.toRoute<CategoryRecipeList>()
                CategoryRecipeListScreen(
                    animatedVisibilityScope = this@composable,
                    strCategory = args.strCategory,
                    strCategoryDescription = args.strCategoryDescription,
                    strCategoryThumb = args.strCategoryThumb,
                    navController = navController,
                    mealListState = mealListState,
                    category = {
                        mealListViewModel.getMealList(it)
                    }
                )
            }
        }
    }
}