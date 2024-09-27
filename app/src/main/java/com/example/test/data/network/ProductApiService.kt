package com.example.test.data.network

import com.example.test.data.model.ProductModelImpl
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("api/ppp/index/")
    suspend fun getProductList(
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int = 0
    ):List<ProductModelImpl>

    @GET("api/ppp/index/")
    suspend fun searchByQuery(@Query("search") query: String): List<ProductModelImpl>
}
