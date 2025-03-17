package com.example.composepoc.presentation.state

import com.example.composepoc.domain.model.ProductDetail

/*
* It's the product details(state) data class which provides various
* flags, data, error w.r.t the various states emitted by the UiState class
* for product details API call via ProductDetailVewModel
* */
data class ProductDetailState(
    val isLoading: Boolean = false, val data: ProductDetail? = null, var error: String = ""
)
