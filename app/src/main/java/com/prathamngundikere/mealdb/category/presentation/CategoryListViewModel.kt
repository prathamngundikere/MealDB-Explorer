package com.prathamngundikere.mealdb.category.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamngundikere.mealdb.category.domain.repository.CategoryListRepository
import com.prathamngundikere.mealdb.category.util.CategoryState
import com.prathamngundikere.mealdb.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val categoryListRepository: CategoryListRepository
): ViewModel() {

    private var _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    init {
        getCategoryList()
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            _categoryState.update {
                it.copy(isLoading = true)
            }

            categoryListRepository.getCategoryList().collectLatest {
                when(it) {
                    is Resource.Error -> {
                        _categoryState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _categoryState.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { categoryList ->
                            _categoryState.update {
                                it.copy(
                                    categoryList = categoryState.value.categoryList + categoryList.shuffled()
                                )
                            }
                        }
                        _categoryState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}