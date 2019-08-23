package com.ericdecanini.sendclothing.mvp.adapter

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ericdecanini.sendclothing.mvp.activity.ProductsActivity
import com.ericdecanini.sendclothing.mvp.model.Product
import kotlinx.android.synthetic.main.grid_item_product.view.*
import timber.log.Timber
import java.net.URL
import android.view.animation.AnimationUtils.loadAnimation
import com.ericdecanini.sendclothing.R


class ProductsAdapter(private val context: ProductsActivity, private val productsList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item_product, parent, false))
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList[position]
        holder.name.text = product.name
        holder.price.text = product.price
        Glide.with(context).load(product.image).into(holder.image)

        holder.itemView.setOnClickListener {
            // Item OnClick
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.product_image
        val name = view.product_name
        val colour = view.product_colour
        val price = view.product_price
    }

}