package com.example.test.data.repository

import android.util.Log
import com.example.test.data.model.mapToProductModel
import com.example.test.data.network.ProductApiService
import com.example.test.domain.model.ProductModel
import com.example.test.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(private val apiService: ProductApiService) : ProductRepository {

    override fun getProductList(offset: Int): Flow<List<ProductModel>> = flow {

        val response = apiService.getProductList(offset)
        val productList = response.map { mapToProductModel(it) }
        emit(productList)

    }

    override fun searchProduct(text: String): Flow<List<ProductModel>> = flow {
        val response = apiService.searchByQuery(text)
        val filteredProducts = response.filter { it.name.contains(text, ignoreCase = true) }
        emit(filteredProducts.map { mapToProductModel(it) })
    }
}
