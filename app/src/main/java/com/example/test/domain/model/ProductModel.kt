package com.example.test.domain.model

data class ProductModel(
    val id:Int,
    val image:String,
    val categories: CategoryModel,
    val name:String,
    val description:String
)
