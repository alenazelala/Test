package com.example.test.domain.useCases

import com.example.test.domain.model.ProductModel
import com.example.test.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class SearchProductUseCase(private val repository: ProductRepository) {
    fun invoke(text: String): Flow<List<ProductModel>> = repository.searchProduct(text)
}