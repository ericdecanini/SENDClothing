package com.ericdecanini.sendclothing.data.products

import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.model.ProductList
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductsApiService {

    @GET("media/catalog/example.json")
    fun getExampleShirts(): Observable<ProductList>

}