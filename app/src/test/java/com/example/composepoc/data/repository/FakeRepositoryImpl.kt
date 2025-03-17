package com.example.composepoc.data.repository

import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.repository.Repository

/**
 * It's a fake implementation of RepositoryImpl
 * It provides all mock data to the test cases
 */
class FakeRepositoryImpl : Repository {
    // List of products
    val item1 = ProductItem(1, "image1", "Shirt", "Description1")
    val item2 = ProductItem(2, "image2", "Saree", "Description2")
    val item3 = ProductItem(3, "image3", "Necklace", "Description3")
    val item4 = ProductItem(4, "image4", "Light", "Description4")
    val listOfProduct = listOf<ProductItem>(item1, item2, item3, item4)

    // Product details
    val item = ProductDetail("category1", "Description1", 1, "image1", "100", "Shirt")

    // Returns the list of products
    override suspend fun getProductList(): List<ProductItem> {
        return listOfProduct
    }

    // Returns a particular product detail based on supplied product id
    override suspend fun getProductDetail(id: Int): ProductDetail {
        return item
    }
}