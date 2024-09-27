package com.example.test.data.model

import com.example.test.domain.model.CategoryModel
import com.example.test.domain.model.ProductModel

fun mapToProductModel(data: ProductModelImpl): ProductModel {
    return ProductModel(
        id = data.id,
        image = data.image,
        categories = mapToCategoryModel(data.categories),
        name = data.name,
        description = data.description
    )
}
fun mapToCategoryModel(data: CategoryModelImpl): CategoryModel {
   return CategoryModel(
        id = data.id,
        icon = data.icon
    )
}