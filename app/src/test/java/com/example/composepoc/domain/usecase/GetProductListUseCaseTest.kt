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
                    print("Inside invoke()")
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
    }

    @Test
    fun testForCorrectProductItem_FromListOfProducts_ReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                    print("Inside Test1()")
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.get(0).id == 1)).isTrue()
        Truth.assertThat((productListItem.get(0).image == "image1")).isTrue()
        Truth.assertThat((productListItem.get(0).title == "Shirt")).isTrue()
        Truth.assertThat((productListItem.get(0).description == "Description1")).isTrue()
    }

    @Test
    fun testForInCorrectProductItem_FromListOfProducts_ReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                    print("Inside Test2()")
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.get(1).id == 0)).isFalse()
        Truth.assertThat((productListItem.get(1).image == "")).isFalse()
        Truth.assertThat((productListItem.get(1).title == "")).isFalse()
        Truth.assertThat((productListItem.get(1).description == "")).isFalse()
    }


    @Test
    fun testIfAnyDuplicateProductItemIsFound_InTheListOfProducts_ReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                    print("Inside Test3()")
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.get(0) == productListItem.get(1))).isFalse()
    }

    @Test
    fun testIfListOfProducts_EmptyOrNot_ReturnedFromAPI(): Unit = runBlocking {
        mockGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    // Loading...
                }

                is UiState.Success -> {
                    // List of products
                    productListItem = it.data!!
                    print("Inside Test4()")
                }

                is UiState.Error -> {
                    // Error
                }
            }
        }
        Truth.assertThat((productListItem.isNotEmpty())).isTrue()
    }

}
