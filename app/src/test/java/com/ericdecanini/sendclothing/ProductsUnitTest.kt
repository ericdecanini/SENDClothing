package com.ericdecanini.sendclothing

import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.presenter.ProductsPresenter
import com.ericdecanini.sendclothing.mvp.view.ProductsView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ProductsUnitTest {

    @Spy
    lateinit var mockedPresenter: ProductsPresenter

    @Mock
    lateinit var mockedView: ProductsView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenSortProducts_thenProductsAreInRightOrder() {
        val productList = ArrayList<Product>()
        val expectedProducts = ArrayList<Product>()
        val actualPrices = ArrayList<String>()
        val expectedPrices = ArrayList<String>()

        mockedPresenter.attachView(mockedView)
        productList.add(Product("", "", "£20", ""))
        productList.add(Product("", "", "£40", ""))
        productList.add(Product("", "", "£30", ""))
        mockedPresenter.products = productList

        // Sort by Price High
        expectedProducts.add(Product("", "", "£40", ""))
        expectedProducts.add(Product("", "", "£30", ""))
        expectedProducts.add(Product("", "", "£20", ""))
        mockedPresenter.sortProducts(1)

        // Map Products to Price Lists
        for (product in mockedPresenter.products)
            actualPrices.add(product.price)
        for (product in expectedProducts)
            expectedPrices.add(product.price)
        assertEquals(expectedPrices, actualPrices)

        // Sort by Price Low
        expectedProducts.clear()
        expectedPrices.clear()
        actualPrices.clear()
        expectedProducts.add(Product("", "", "£20", ""))
        expectedProducts.add(Product("", "", "£30", ""))
        expectedProducts.add(Product("", "", "£40", ""))

        // Map Products to Price Lists
        mockedPresenter.sortProducts(2)
        for (product in mockedPresenter.products)
            actualPrices.add(product.price)
        for (product in expectedProducts)
            expectedPrices.add(product.price)
        assertEquals(expectedPrices, actualPrices)
    }

}
