package com.example.composepoc.presentation.viewmodel

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.model.ProductItem
import com.example.composepoc.domain.usecase.GetProductListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class ProductListVewModelTest {
    private lateinit var productListVewModel: ProductListVewModel
    private lateinit var apiService: ApiService
    private lateinit var repositoryImpl: RepositoryImpl
    private val productListUseCase = mockk<GetProductListUseCase>(relaxed = true)

    @Before
    fun setUp() {
        apiService = mockk()
        repositoryImpl = RepositoryImpl(apiService)
        productListVewModel = mockk<ProductListVewModel>()
    }

    @Test
    fun testGetProductListViewModel() = runBlocking {
        // Arrange
        var productListResult: List<ProductItem> = listOf()
        val productId = 1
        val productImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        val productTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
        val productDescription =
            "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"
        val productItem = ProductItem(productId, productImage, productTitle, productDescription)
        val productItem2 = ProductItem(productId, productImage, productTitle, productDescription)
        val listOfProduct = listOf(productItem, productItem2)

        coEvery { productListUseCase.invoke() } returns flowOf(UiState.Success(listOfProduct))

        //  Act
        val job = launch {
            productListResult = productListVewModel.productList.value.data!!
        }

        // Assert
        assertNotEquals(productListResult, listOfProduct)
        job.cancel()


    }

}