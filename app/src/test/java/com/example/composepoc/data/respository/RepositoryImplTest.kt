package com.example.composepoc.data.respository

import com.example.composepoc.core.common.toProductDetailsItem
import com.example.composepoc.core.common.toProductListItem
import com.example.composepoc.data.model.ProductListDTO
import com.example.composepoc.data.netwotk.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * It is the test implementation of RepositoryImpl
 * where productListItems & productDetails APIs are executed
 */
class RepositoryImplTest {
    private lateinit var apiService: ApiService
    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        repositoryImpl = RepositoryImpl(apiService)
    }

    @Test
    fun testGetProductListItemsAPI() = runBlocking {
        // Arrange
        val productId = 1
        val productImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        val productTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
        val productDescription =
            "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"
        val productCategory = "men's clothing"
        val productPrice = "109.95"
        val productItemDTO = ProductListDTO(
            productCategory, productDescription, productId, productImage, productPrice, productTitle
        )
        val productItemDTO1 = ProductListDTO(
            productCategory, productDescription, productId, productImage, productPrice, productTitle
        )
        coEvery { apiService.getAllProductListAPI() } returns listOf(
            productItemDTO, productItemDTO1
        )

        // Act
        val itemFromActualProductList = repositoryImpl.getProductList()

        // Assert
        assertEquals(productItemDTO.toProductListItem(), itemFromActualProductList[0])
        assertEquals(2, itemFromActualProductList.size)
    }

    @Test
    fun testGetProductDetailsAPI() = runBlocking {
        // Arrange
        val productId = 3
        val productImage = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
        val productTitle = "Mens Cotton Jacket"
        val productDescription =
            "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day."
        val productCategory = "men's clothing"
        val productPrice = "55.99"
        val productItemDTO = ProductListDTO(
            productCategory, productDescription, productId, productImage, productPrice, productTitle
        )
        coEvery { apiService.getProductDetailsAPI(productId) } returns productItemDTO

        // Act
        val actualProductDetails = repositoryImpl.getProductDetail(productId)

        // Assert
        assertEquals(productItemDTO.toProductDetailsItem(), actualProductDetails)
    }

}