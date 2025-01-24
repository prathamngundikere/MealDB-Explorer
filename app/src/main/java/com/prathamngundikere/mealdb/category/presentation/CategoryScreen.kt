package com.prathamngundikere.mealdb.category.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prathamngundikere.mealdb.category.util.CategoryState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CategoryScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    categoryState: CategoryState,
    navController: NavController
) {

    val rememberLazyListState = rememberLazyListState()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meal Category",
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        if (categoryState.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
                state = rememberLazyListState,
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(
                    items = categoryState.categoryList,
                    key = { it.idCategory }
                ) {
                    CategoryItem(
                        animatedVisibilityScope = animatedVisibilityScope,
                        modifier = Modifier.animateItem(),
                        category = it,
                        navController = navController
                    )
                }
            }
        }
    }
}