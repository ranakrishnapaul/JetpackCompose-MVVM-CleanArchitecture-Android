package com.example.composepoc.domain.usecase

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.repository.MockRepositoryImpl
import com.example.composepoc.domain.model.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * It's a mock implementation of GetProductListUseCase
 * It provides all the mechanism similar to real
 * GetProductListUseCase to the respective test case
 */
class MockGetProductListUseCase @Inject constructor(private val repositoryImpl: MockRepositoryImpl) {

    operator fun invoke(): Flow<UiState<List<ProductItem>>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getProductList()))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}