package com.example.test.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val gson = GsonBuilder()
    .serializeNulls()
    .create()

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://shans.d2.i-partner.ru/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun productApiService(retrofit: Retrofit): ProductApiService {
    return retrofit.create(ProductApiService::class.java)
}