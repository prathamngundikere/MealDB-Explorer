package com.prathamngundikere.mealdb.recipeList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.recipeList.domain.repository.MealListRepository
import com.prathamngundikere.mealdb.recipeList.util.MealListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val mealListRepository: MealListRepository
): ViewModel() {

    private val _mealListState = MutableStateFlow(MealListState())
    val mealListState = _mealListState.asStateFlow()

     fun getMealList(category: String) {

        viewModelScope.launch {

            _mealListState.update {
                it.copy(isLoading = true)
            }

            mealListRepository.getMealList(category).collectLatest {
                when(it) {
                    is Resource.Error -> {
                        _mealListState.update {
                            it.copy(error = "Failed to Load meals - " + it.error)
                        }
                    }
                    is Resource.Loading -> {
                        _mealListState.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { mealList ->
                            _mealListState.update {
                                it.copy(
                                    mealList = mealList.shuffled()
                                )
                            }
                        }
                        _mealListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}