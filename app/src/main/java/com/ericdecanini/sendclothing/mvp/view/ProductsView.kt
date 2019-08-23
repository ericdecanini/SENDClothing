package com.ericdecanini.sendclothing.mvp.view

import com.ericdecanini.sendclothing.mvp.base.ui.BaseView
import com.ericdecanini.sendclothing.mvp.model.Product

interface ProductsView: BaseView {

    fun showLoading()

    fun displayProducts(products: List<Product>)

    fun setViewSize(viewSize: Int)

}