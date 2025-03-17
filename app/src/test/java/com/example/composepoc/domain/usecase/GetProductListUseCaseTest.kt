package com.example.composepoc.domain.usecase

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.repository.FakeRepositoryImpl
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
    private lateinit var fakeGetProductListUseCase: FakeGetProductListUseCase
    private lateinit var fakeRepositoryImpl: FakeRepositoryImpl
    private lateinit var productListItem: List<ProductItem>

    @Before
    fun setUp() {
        fakeRepositoryImpl = FakeRepositoryImpl()
        fakeGetProductListUseCase = FakeGetProductListUseCase(fakeRepositoryImpl)
        productListItem = fakeRepositoryImpl.listOfProduct
    }

    @Test
    fun invoke() {
        fakeGetProductListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {

                }

                is UiState.Success -> {
                    productListItem = it.data!!
                }

                is UiState.Error -> {

                }
            }
        }
    }

    @Test
    fun `GIVEN 'check the correct product title' WHEN 'correct product list returns' THEN returns true `(): Unit =
        runBlocking {
            fakeGetProductListUseCase.invoke().onEach {
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
            Truth.assertThat((productListItem.get(0).title.equals("Shirt"))).isTrue()
        }

    @Test
    fun `GIVEN 'check the incorrect product title' WHEN 'correct product list returns' THEN returns false `(): Unit =
        runBlocking {
            fakeGetProductListUseCase.invoke().onEach {
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
            Truth.assertThat((productListItem.get(1).title.equals("Shirt"))).isFalse()
        }
}
