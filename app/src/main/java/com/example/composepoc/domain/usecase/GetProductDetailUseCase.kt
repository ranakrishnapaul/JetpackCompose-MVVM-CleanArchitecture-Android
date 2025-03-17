package com.example.composepoc.domain.usecase

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * It's the GetProductDetailUseCase class injected at the constructor level
 *  w.r.t the application context where the product details API gets called through
 * Repository Implementation & it acts as an intermediate mechanism in between
 * ViewModel & Repository(Where the respective REST API gets called)
 */
class GetProductDetailUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    operator fun invoke(id: Int): Flow<UiState<ProductDetail>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getProductDetail(id)))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}