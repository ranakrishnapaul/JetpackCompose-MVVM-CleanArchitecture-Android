package com.example.composepoc.domain.di

import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.usecase.GetProductDetailUseCase
import com.example.composepoc.domain.usecase.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
* It creates the UseCase Module(Retrofit client)
* as a singleton component & it's various methods,
* provided to any module(caller) injected at the application level
* */
@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun productListUseCaseProvider(repositoryImpl: RepositoryImpl): GetProductListUseCase {
        return GetProductListUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun productDetailUseCaseProvider(repositoryImpl: RepositoryImpl): GetProductDetailUseCase {
        return GetProductDetailUseCase(repositoryImpl)
    }

}