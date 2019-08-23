package com.ericdecanini.sendclothing.data.products

import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.model.ProductList
import io.reactivex.Observable
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class ProductsRepository @Inject constructor(val retrofit: Retrofit): IProductsRepository {

    var productsApiService: ProductsApiService = retrofit.create(ProductsApiService::class.java)

    override fun logBaseUrl() {
        Timber.d("Products Repository Functions with ${retrofit.baseUrl()}")
    }

    override fun getExampleShirts(): Observable<List<Product>> {
        return productsApiService.getExampleShirts()
            .map { productlist -> productlist.products }
    }

}