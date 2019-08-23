package com.ericdecanini.sendclothing.mvp.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ericdecanini.sendclothing.app.SendClothingApp
import com.ericdecanini.sendclothing.mvp.adapter.ProductsAdapter
import com.ericdecanini.sendclothing.mvp.base.ui.BaseActivity
import com.ericdecanini.sendclothing.mvp.model.Product
import com.ericdecanini.sendclothing.mvp.presenter.ProductsPresenter
import com.ericdecanini.sendclothing.mvp.view.ProductsView
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.dialog_nointernet.*
import javax.inject.Inject
import android.view.animation.AnimationUtils.loadLayoutAnimation
import android.view.animation.LayoutAnimationController
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ericdecanini.sendclothing.R
import kotlinx.android.synthetic.main.drawer_filter.*


class ProductsActivity : BaseActivity(), ProductsView {

    @Inject
    lateinit var mPresenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        SendClothingApp.get(this).getComponent().inject(this)
        initToolbar()

        mPresenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.getSampleShirts()
    }

    private fun initToolbar() {
        // Init Action Bar
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        window.statusBarColor = getColor(R.color.colorPrimary)
        // REPLACE WITH TITLE FROM INTENT IN REAL APP
        toolbar_title.text = getString(R.string.toolbar_title_sample)

        // Init Options Toolbar
        nav_sort.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_sort, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {item ->
                mPresenter.sortProducts(item.itemId)
                true
            }
        }

        nav_view.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu_view, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { item ->
                mPresenter.changeViewType(item.itemId)
                true
            }
        }

        // Init Filter Navigation View
        nav_filter.setOnClickListener {
            drawer.openDrawer(navdrawer_view)
        }
        ic_close.setOnClickListener { drawer.closeDrawer(navdrawer_view) }
        // Yeah I didn't get much data from the API to play with
        // What else could I do? More random facts of course!
        filter_brand.setOnClickListener { mPresenter.toastRandomFact() }
        filter_department.setOnClickListener { mPresenter.toastRandomFact() }
        filter_colour.setOnClickListener { mPresenter.toastRandomFact() }
        filter_gender.setOnClickListener { mPresenter.toastRandomFact() }
        filter_clothingsize.setOnClickListener { mPresenter.toastRandomFact() }
    }

    override fun setViewSize(viewSize: Int) {
        val layoutManager: RecyclerView.LayoutManager
        if (viewSize == 0)
            layoutManager = GridLayoutManager(this, 2)
        else layoutManager = LinearLayoutManager(this)
        recycler_products.layoutManager = layoutManager
        recycler_products.adapter?.notifyDataSetChanged()
    }

    override fun showNoInternetConnection() {
        progressbar_products.visibility = View.GONE
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_nointernet)
        val dialogButton = dialog.dialog_ok
        dialogButton.setOnClickListener {
            dialog.dismiss()
            mPresenter.getSampleShirts()
        }
        dialog.show()
    }


    override fun showLoading() {
        progressbar_products.visibility = View.VISIBLE
    }

    override fun displayProducts(products: List<Product>) {
        progressbar_products.visibility = View.GONE
        text_productcount.text = String.format("%d items", products.size)
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = ProductsAdapter(this, products)
        recycler_products.layoutManager = layoutManager
        recycler_products.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.clearDisposables()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_products, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_help) {
            mPresenter.toastRandomFact()
            return true
        }
        return false
    }

}
