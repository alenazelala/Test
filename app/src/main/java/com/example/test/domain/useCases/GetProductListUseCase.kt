package com.example.test.domain.useCases

import com.example.test.domain.repository.ProductRepository
import com.example.test.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

class GetProductListUseCase(private val productRepository: ProductRepository) {
    fun invoke(offset: Int): Flow<List<ProductModel>> = productRepository.getProductList(offset)
}