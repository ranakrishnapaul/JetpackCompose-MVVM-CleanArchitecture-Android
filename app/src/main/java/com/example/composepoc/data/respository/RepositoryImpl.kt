package com.example.composepoc.data.respository

import com.example.composepoc.core.common.toProductDetailsItem
import com.example.composepoc.core.common.toProductListItem
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.repository.Repository
import javax.inject.Inject

/**
 * It's the implementation of Repository injected at the constructor level
 * w.r.t the application context where the actual API gets called & respective mapping
 * mechanism is executed upon receiving the response from the API.
 */
class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getProductList(): List<ProductItem> {
        return apiService.getAllProductListAPI().map { it.toProductListItem() }
    }

    override suspend fun getProductDetail(id: Int): ProductDetail {
        return apiService.getProductDetailsAPI(id).toProductDetailsItem()
    }

}