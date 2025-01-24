package com.prathamngundikere.mealdb.core.presentation

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

@Composable
fun MainScreen(){

    val navController = rememberNavController()

    val categoryListViewModel = hiltViewModel<CategoryListViewModel>()
    val categoryState = categoryListViewModel.categoryState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = CategoryScreen
    ) {
        composable<CategoryScreen> {
            CategoryScreen(
                categoryState = categoryState,
                navController = navController
            )
        }
        composable<CategoryRecipeList> {
            val args = it.toRoute<CategoryRecipeList>()
            CategoryRecipeListScreen(
                strCategory = args.strCategory,
                strCategoryDescription = args.strCategoryDescription,
                strCategoryThumb = args.strCategoryThumb,
                navController = navController
            )
        }
    }
}