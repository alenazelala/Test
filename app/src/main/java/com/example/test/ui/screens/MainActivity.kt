package com.example.test.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val productsViewModel: ProductsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(emptyList())
        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition()
                        == productAdapter.itemCount - 1
                    ) {
                        productsViewModel.getProductList()
                    }
                }
            })
        }
        lifecycleScope.launch {
            productsViewModel.productList.collect { productList ->
                productAdapter.updateProducts(productList)
            }
        }
        binding.search.addTextChangedListener {
            productsViewModel.searchProducts(binding.search.text.toString())
        }
        binding.imageView3.setOnClickListener {
            if (binding.search.visibility == View.VISIBLE) {
                binding.search.visibility = View.GONE
            } else {
                binding.search.visibility = View.VISIBLE
            }
        }
        productsViewModel.getProductList()


    }
}