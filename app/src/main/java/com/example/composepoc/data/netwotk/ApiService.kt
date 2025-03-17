package com.example.composepoc.data.netwotk

import com.example.composepoc.data.model.ProductListDTO
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * It's an interface for providing all the mechanism
 * to make the respective API calls from the Use Cases
 */
interface ApiService {

    @GET("/products")
    suspend fun getAllProductListAPI(): List<ProductListDTO>

    @GET("/products/{Id}")
    suspend fun getProductDetailsAPI(@Path("Id") id: Int): ProductListDTO

}