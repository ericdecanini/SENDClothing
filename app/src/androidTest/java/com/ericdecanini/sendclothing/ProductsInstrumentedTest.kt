package com.ericdecanini.sendclothing

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import org.robolectric.Robolectric
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ericdecanini.sendclothing.mvp.activity.ProductsActivity
import com.ericdecanini.sendclothing.mvp.base.ui.BaseActivity
import com.ericdecanini.sendclothing.mvp.model.Product

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ProductsInstrumentedTest {

    lateinit var activity: ProductsActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(ProductsActivity::class.java)
    }

    @Test
    fun whenListIsLoaded_thenNumberOfItemsIsShown() {
        val products = ArrayList<Product>()
        products.add(Product("1", "Test 1", "£20", ""))
        products.add(Product("2", "Test 2", "£40", ""))
        products.add(Product("3", "Test 3", "£30", ""))

        activity.displayProducts(products)
        onView(withId(R.id.text_productcount)).check(matches(withText("3 items")))
    }

}
