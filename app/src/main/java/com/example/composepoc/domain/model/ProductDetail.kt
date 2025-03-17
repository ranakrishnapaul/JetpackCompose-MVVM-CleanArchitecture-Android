package com.example.composepoc.domain.model

/**
 * It's a data model based on the converted data(By Product details Use case)
 * against the response(DTO) received from the Product details API(REST API)
 */
data class ProductDetail(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
)