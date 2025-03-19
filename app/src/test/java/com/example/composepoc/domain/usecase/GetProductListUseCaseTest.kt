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

        productListItem.forEach {
            when (it.id) {
                1 -> {
                    Truth.assertThat(it.image == "image1").isTrue()
                    Truth.assertThat(it.title == "Shirt").isTrue()
                    Truth.assertThat(it.description == "Description1").isTrue()
                }

                2 -> {
                    Truth.assertThat(it.image == "image2").isTrue()
                    Truth.assertThat(it.title == "Jacket").isTrue()
                    Truth.assertThat(it.description == "Description2").isTrue()
                }

                3 -> {
                    Truth.assertThat(it.image == "image3").isTrue()
                    Truth.assertThat(it.title == "Laptop").isTrue()
                    Truth.assertThat(it.description == "Description3").isTrue()
                }

                4 -> {
                    Truth.assertThat(it.image == "image4").isTrue()
                    Truth.assertThat(it.title == "TV").isTrue()
                    Truth.assertThat(it.description == "Description4").isTrue()
                }

            }
        }
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

        productListItem.forEach {
            when (it.id) {
                1 -> {
                    Truth.assertThat(it.image == "").isFalse()
                    Truth.assertThat(it.title == "").isFalse()
                    Truth.assertThat(it.description == "").isFalse()
                }

                2 -> {
                    Truth.assertThat(it.image == "image20").isFalse()
                    Truth.assertThat(it.title == "Jacket123").isFalse()
                    Truth.assertThat(it.description == "Description123").isFalse()
                }

                3 -> {
                    Truth.assertThat(it.image == "image30").isFalse()
                    Truth.assertThat(it.title == "Laptop123").isFalse()
                    Truth.assertThat(it.description == "Description145").isFalse()
                }

                4 -> {
                    Truth.assertThat(it.image == "image40").isFalse()
                    Truth.assertThat(it.title == "TV123").isFalse()
                    Truth.assertThat(it.description == "Description123").isFalse()
                }

            }
        }
    }


    @Test
    fun testIfAnyDuplicateProductItemIsFound_InTheListOfProducts_ReturnedFromAPI(): Unit =
        runBlocking {
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

            productListItem.forEach {
                when (it.id) {
                    1 -> {
                        Truth.assertThat((productListItem.get(0))).isNoneOf(
                            productListItem.get(1), productListItem.get(2), productListItem.get(3)
                        )
                    }

                    2 -> {
                        Truth.assertThat((productListItem.get(1))).isNoneOf(
                            productListItem.get(0), productListItem.get(2), productListItem.get(3)
                        )
                    }

                    3 -> {
                        Truth.assertThat((productListItem.get(2))).isNoneOf(
                            productListItem.get(0), productListItem.get(1), productListItem.get(3)
                        )
                    }

                    4 -> {
                        Truth.assertThat((productListItem.get(3))).isNoneOf(
                            productListItem.get(0), productListItem.get(1), productListItem.get(2)
                        )
                    }

                }
            }
        }

}
