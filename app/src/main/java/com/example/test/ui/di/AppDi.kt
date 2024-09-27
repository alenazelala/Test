package com.example.test.ui.di

import com.example.test.data.repository.ProductRepositoryImpl
import com.example.test.data.network.productApiService
import com.example.test.data.network.provideRetrofit
import com.example.test.domain.useCases.GetProductListUseCase
import com.example.test.domain.repository.ProductRepository
import com.example.test.domain.useCases.SearchProductUseCase
import com.example.test.ui.screens.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val networkModule = module {
    single { provideRetrofit() }
    single { productApiService(get()) }
}
val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}
val useCasesModule = module {
    single { GetProductListUseCase(get()) }
    single { SearchProductUseCase(get()) }
}
val viewModelsModule = module {
    viewModel { ProductsViewModel(get(), get()) }
}