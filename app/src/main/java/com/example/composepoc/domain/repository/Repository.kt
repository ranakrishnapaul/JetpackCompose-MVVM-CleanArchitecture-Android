package com.example.composepoc.domain.repository

import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem

/*
* It's an interface of Repository which provides
* the abstract methods of making respective API calls
* such as Product list & Product details upon implementation
* */
interface Repository {

    suspend fun getProductList(): List<ProductItem>

    suspend fun getProductDetail(id: Int): ProductDetail

}