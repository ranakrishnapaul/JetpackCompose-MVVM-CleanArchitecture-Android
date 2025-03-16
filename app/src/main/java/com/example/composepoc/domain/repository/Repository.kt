package com.example.composepoc.domain.repository

import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem

interface Repository {

    suspend fun getProductList() : List<ProductItem>

    suspend fun getProductDetail(id : Int) : ProductDetail

}