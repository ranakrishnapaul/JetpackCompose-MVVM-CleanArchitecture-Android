package com.example.composepoc.domain.usecase

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.repository.MockRepositoryImpl
import com.example.composepoc.domain.model.ProductItem
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * It's the Test case for GetProductListUseCase to validate
 * all the logics(REST API calls) provided by GetProductListUseCase
 */
class GetProductListUseCaseTest {
    private lateinit var mockGetProductListUseCase: MockGetProductListUseCase
    private lateinit var mockRepositoryImpl: MockRepositoryImpl
    private lateinit var productListItem: List<ProductItem>

    @Before
    fun setUp() {
        mockRepositoryImpl = MockRepositoryImpl()
        mockGetProductListUseCase = MockGetProductListUseCase(mockRepositoryImpl)
        productListItem = mockRepositoryImpl.listOfProduct
    }

    @Test
    fun invoke() {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
    }

    @Test
    fun testForCorrectProductItemFromListOfProductsReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.get(0).id.equals(1))).isTrue()
        Truth.assertThat((productListItem.get(0).image.equals("image1"))).isTrue()
        Truth.assertThat((productListItem.get(0).title.equals("Shirt"))).isTrue()
        Truth.assertThat((productListItem.get(0).description.equals("Description1"))).isTrue()
    }

    @Test
    fun testForInCorrectProductItemFromListOfProductsReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.get(1).id.equals(1))).isFalse()
        Truth.assertThat((productListItem.get(1).image.equals("image1"))).isFalse()
        Truth.assertThat((productListItem.get(1).title.equals("Shirt"))).isFalse()
        Truth.assertThat((productListItem.get(1).description.equals("Description1"))).isFalse()
    }
}
