package com.example.test.data.model

data class ProductModelImpl(
    val id:Int,
    val image:String,
    val categories: CategoryModelImpl,
    val name:String,
    val description:String
)