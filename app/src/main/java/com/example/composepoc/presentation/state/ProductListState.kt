package com.example.composepoc.presentation.state

import com.example.composepoc.domain.model.ProductItem

/*
* It's the product list(state) data class which provides various
* flags, data, error w.r.t the various states emitted by the UiState class
* for product listing API call via ProductListVewModel
* */
data class ProductListState(
    val isLoading: Boolean = false, val data: List<ProductItem>? = null, var error: String = ""
)
