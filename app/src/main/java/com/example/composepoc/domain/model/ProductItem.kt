package com.example.composepoc.domain.model

/*
* It's a data model based on the converted data(By Product item Use case)
* against the response(DTO) received from the Product list API(REST API)
* */
data class ProductItem(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
)