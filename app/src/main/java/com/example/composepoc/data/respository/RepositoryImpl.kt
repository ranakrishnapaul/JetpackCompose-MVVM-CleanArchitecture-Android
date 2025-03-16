package com.example.composepoc.data.respository

import com.example.composepoc.core.common.toProductDetail
import com.example.composepoc.core.common.toProductList
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getProductList(): List<ProductItem> {
       return apiService.getAllProductListAPI().map { it.toProductList() }
    }

    override suspend fun getProductDetail(id: Int): ProductDetail {
        return apiService.getProductDetailsAPI(id).toProductDetail()
    }

}