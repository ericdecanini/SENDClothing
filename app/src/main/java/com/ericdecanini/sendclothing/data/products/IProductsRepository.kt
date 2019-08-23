package com.ericdecanini.sendclothing.data.products

import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.model.ProductList
import io.reactivex.Observable
import retrofit2.http.GET

interface IProductsRepository {

    fun logBaseUrl()

    fun getExampleShirts(): Observable<List<Product>>

}