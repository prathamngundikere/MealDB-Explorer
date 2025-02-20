package com.prathamngundikere.mealdb.recipeList.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.prathamngundikere.mealdb.recipeList.util.MealListState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CategoryRecipeListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    strCategory: String,
    strCategoryDescription: String,
    strCategoryThumb: String,
    navController: NavController,
    mealListState: MealListState,
    category: (String) -> Unit
) {

    LaunchedEffect(strCategory) {
        category(strCategory)
    }

    val rememberLazyListState = rememberLazyListState()

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(strCategoryThumb)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = strCategory),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        text = strCategory,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "navigate back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        modifier = modifier
            .sharedBounds(
                sharedContentState = rememberSharedContentState(
                    key = strCategory + "color"
                ),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .fillMaxSize()
            .background(
            color = MaterialTheme.colorScheme.surface
            ),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = MaterialTheme.colorScheme.surface
                ),
            state = rememberLazyListState,
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item(
                key = 0
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .sharedElement(
                            state = rememberSharedContentState(key = strCategoryThumb),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageState is AsyncImagePainter.State.Error) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    if (imageState is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator()
                    }
                    if (imageState is AsyncImagePainter.State.Empty) {
                        CircularProgressIndicator()
                    }
                    if (imageState is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(28.dp)),
                            painter = imageState.painter,
                            contentDescription = strCategory,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            item(key = 1) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = strCategoryDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            if (mealListState.error != null) {
                item(key = 3) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = mealListState.error,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            if (mealListState.isLoading) {
                item(key = 4) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                items(
                    items = mealListState.mealList,
                    key = { it.idMeal }
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        color = MaterialTheme.colorScheme.outlineVariant,
                        thickness = 1.dp
                    )
                    RecipeListItem(
                        animatedVisibilityScope = animatedVisibilityScope,
                        meal = it,
                        navController = navController
                    )
                }
            }
            item(key = 5) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(20.dp),
                    contentAlignment = Alignment.TopCenter
                ) {}
            }
        }
    }
}