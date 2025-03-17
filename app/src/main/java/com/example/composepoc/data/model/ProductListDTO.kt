package com.example.composepoc.data.model

/*
* It's a data model based on the response(DTO)
* received from the Product list API(REST API)
* */
data class ProductListDTO(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
)