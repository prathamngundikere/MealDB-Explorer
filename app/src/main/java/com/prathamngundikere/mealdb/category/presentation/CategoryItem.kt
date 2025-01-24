package com.prathamngundikere.mealdb.category.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.prathamngundikere.mealdb.category.domain.model.Category
import com.prathamngundikere.mealdb.core.util.CategoryRecipeList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CategoryItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    category: Category,
    navController: NavController
) {

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(category.strCategoryThumb)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .clickable(
                enabled = true,
                onClick = {
                    navController.navigate(CategoryRecipeList(
                        strCategory = category.strCategory,
                        strCategoryDescription = category.strCategoryDescription,
                        strCategoryThumb = category.strCategoryThumb
                    ))
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceBright
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(color = MaterialTheme.colorScheme.surfaceBright),
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
                            .sharedElement(
                                state = rememberSharedContentState(key = category.strCategoryThumb),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(28.dp)),
                        painter = imageState.painter,
                        contentDescription = category.idCategory,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = category.strCategory),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = category.strCategory,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = category.strCategoryDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(15.dp))

        }
    }
}