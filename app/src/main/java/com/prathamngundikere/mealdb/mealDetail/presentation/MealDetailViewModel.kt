package com.prathamngundikere.mealdb.mealDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamngundikere.mealdb.core.util.Resource
import com.prathamngundikere.mealdb.mealDetail.domain.repository.MealDetailRepository
import com.prathamngundikere.mealdb.mealDetail.util.MealDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val mealDetailRepository: MealDetailRepository
): ViewModel() {

    private val _mealDetailState = MutableStateFlow(MealDetailState())
    val mealDetailState = _mealDetailState.asStateFlow()

    fun getMealDetail(idMeal: String) {
        viewModelScope.launch {
            _mealDetailState.update {
                it.copy(isLoading = true)
            }
            mealDetailRepository.getMealDetail(idMeal).collectLatest {
                when(it) {
                    is Resource.Error -> {
                        _mealDetailState.update {
                            it.copy(error = "Failed to Load Meal Detail - "+ it.error)
                        }
                    }
                    is Resource.Loading -> {
                        _mealDetailState.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { mealDetailList ->
                            _mealDetailState.update {
                                it.copy(
                                    mealDetail = mealDetailList[0]
                                )
                            }
                        }
                        _mealDetailState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}