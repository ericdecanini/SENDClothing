package com.ericdecanini.sendclothing.mvp.presenter

import android.widget.Toast
import com.blankj.utilcode.util.NetworkUtils
import com.ericdecanini.sendclothing.R
import com.ericdecanini.sendclothing.data.products.ProductsRepository
import com.ericdecanini.sendclothing.mvp.base.presenter.BasePresenter
import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.view.ProductsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


open class ProductsPresenter @Inject constructor(val mProductsRepository: ProductsRepository?): BasePresenter<ProductsView>() {

    constructor(): this(null)

    val disposable = CompositeDisposable()
    var products = ArrayList<Product>().toList()

    fun getSampleShirts() {
        checkViewAttached()
        if (!NetworkUtils.isConnected()) {
            getMvpView()!!.showNoInternetConnection()
            return
        }
        // TODO: Add RxUtil here
        getMvpView()!!.showLoading()

        disposable.add(mProductsRepository!!.getExampleShirts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { products ->
                this.products = products
                getMvpView()!!.displayProducts(products)
            })
    }

    // If you really want that max performance algorithm stuff
    // I can write a more fancy quick or merge type sort
    // ... Yeah I went to college
    fun sortProducts(sortType: Int) {
        checkViewAttached()
        val tempProducts = ArrayList<Product>(products)
        when(sortType) {
            1, R.id.menu_sort_pricehigh -> tempProducts.sortWith(Comparator { p1, p2 ->
                    p2.price.compareTo(p1.price)
                })

            2, R.id.menu_sort_pricelow -> tempProducts.sortWith(Comparator { p1, p2 ->
                p1.price.compareTo(p2.price)
            })

            else -> toastRandomFact()
        }

        products = tempProducts
        getMvpView()!!.displayProducts(products)
    }

    fun changeViewType(viewType: Int) {
        when(viewType) {
            R.id.menu_view_large -> {
                getMvpView()!!.setViewSize(1)
            }
            R.id.menu_view_small -> {
                getMvpView()!!.setViewSize(0)
            }
            else -> toastRandomFact()
        }
    }

}