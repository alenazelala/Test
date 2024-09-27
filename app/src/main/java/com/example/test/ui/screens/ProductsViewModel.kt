package com.example.test.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.useCases.GetProductListUseCase
import com.example.test.domain.model.ProductModel
import com.example.test.domain.useCases.SearchProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductModel>>(emptyList())
    val productList: StateFlow<List<ProductModel>> = _productList

    private var currentPage = 1
    private var isLoading = false
    private var hasMoreData = true


    fun getProductList() = viewModelScope.launch {
        if (isLoading || !hasMoreData) return@launch

        isLoading = true
        try {
            getProductListUseCase.invoke(offset = currentPage * 5).collect { products ->

                if (products.isEmpty()) {
                    hasMoreData = false
                } else {
                    val currentProducts = _productList.value.toMutableList()
                    val newProducts = products.filterNot { it in currentProducts }

                    _productList.value = currentProducts + newProducts
                    currentPage++
                }
            }
        } catch (e: Exception) {
            Log.e("ProductsViewModel", "getProductList: Ошибка при получении списка продуктов", e)
        } finally {
            isLoading = false
        }
    }

    fun searchProducts(query: String) = viewModelScope.launch {
        searchProductUseCase.invoke(query).collect { products ->
            _productList.value = products
            currentPage = 0
        }
    }
}
