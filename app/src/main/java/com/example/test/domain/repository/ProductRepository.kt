package com.example.test.domain.repository

import com.example.test.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    fun getProductList( offset: Int): Flow<List<ProductModel>>

    fun searchProduct(text:String): Flow<List<ProductModel>>
}