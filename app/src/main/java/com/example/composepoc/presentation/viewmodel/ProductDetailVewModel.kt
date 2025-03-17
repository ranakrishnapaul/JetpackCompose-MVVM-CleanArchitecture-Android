package com.example.composepoc.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepoc.core.common.UiState
import com.example.composepoc.domain.usecase.GetProductDetailUseCase
import com.example.composepoc.presentation.state.ProductDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * It's a VewModel class which is injected at constructor level
 * w.r.t the application context & acts an intermediate mechanism
 * in between view(UI layer) & API(data layer) with the help of GetProductDetailUseCase
 * and listens for any data changes/updates available from the data layer
 * & propagates the same to the UI layer accordingly
 */
@HiltViewModel
class ProductDetailVewModel @Inject constructor(private val productDetailUseCase: GetProductDetailUseCase) :
    ViewModel() {

    private val _productDetail = mutableStateOf(ProductDetailState())
    val productDetail: State<ProductDetailState> get() = _productDetail


    fun getProductDetailAPi(id: Int) {
        productDetailUseCase.invoke(id).onEach {
            when (it) {
                is UiState.Loading -> {
                    _productDetail.value = ProductDetailState(isLoading = true)
                }

                is UiState.Success -> {
                    _productDetail.value = ProductDetailState(data = it.data)
                }

                is UiState.Error -> {
                    _productDetail.value = ProductDetailState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}