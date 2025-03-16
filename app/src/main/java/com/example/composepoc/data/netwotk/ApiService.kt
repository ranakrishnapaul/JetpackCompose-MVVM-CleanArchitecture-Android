package com.example.composepoc.data.netwotk

import com.example.composepoc.data.model.ProductListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/products")
    suspend fun getAllProductListAPI() : List<ProductListDTO>

    @GET("/products/{Id}")
    suspend fun getProductDetailsAPI(@Path("Id") id : Int) : ProductListDTO

}