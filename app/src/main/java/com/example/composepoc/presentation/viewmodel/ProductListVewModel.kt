package com.example.composepoc.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductListUseCase
import com.example.composepoc.presentation.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * It's a VewModel class which is injected at constructor level
 * w.r.t the application context & acts an intermediate mechanism
 * in between view(UI layer) & API(data layer) with the help of GetProductListUseCase
 * and listens for any data changes/updates available from the data layer
 * & propagates the same to the UI layer accordingly
 */
@HiltViewModel
class ProductListVewModel @Inject constructor(private val productListUseCase: GetProductListUseCase) :
    ViewModel() {

    private val _productList = mutableStateOf(ProductListState())
    val productList: State<ProductListState> get() = _productList

    init {
        productListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    _productList.value = ProductListState(isLoading = true)
                }

                is UiState.Success -> {
                    _productList.value = ProductListState(data = it.data)
                }

                is UiState.Error -> {
                    _productList.value = ProductListState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}