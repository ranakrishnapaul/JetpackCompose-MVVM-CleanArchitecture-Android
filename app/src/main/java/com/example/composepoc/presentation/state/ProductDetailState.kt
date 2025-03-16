package com.example.composepoc.presentation.state

import com.example.composepoc.domain.model.ProductDetail

data class ProductDetailState(val isLoading : Boolean = false,
                              val data : ProductDetail? = null,
                              var error : String ="")
