package com.example.test.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ProductCardBinding
import com.example.test.domain.model.ProductModel

class ProductAdapter(private var productList: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(val binding: ProductCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    fun updateProducts(newProductList: List<ProductModel>) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.textName.text = product.name
        holder.binding.textDescription.text = product.description
        Glide.with(holder.binding.imageProduct.context)
            .load("http://shans.d2.i-partner.ru/" + product.image)
            .into(holder.binding.imageProduct)
        Glide.with(holder.binding.imageCategory.context)
            .load("http://shans.d2.i-partner.ru/" + product.categories.icon)
            .into(holder.binding.imageCategory)
//        holder.binding..setOnClickListener {
//            // Логика нажатия на кнопку
//        }

    }

}