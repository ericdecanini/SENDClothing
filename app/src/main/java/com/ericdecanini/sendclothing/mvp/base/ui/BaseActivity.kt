package com.ericdecanini.sendclothing.mvp.base.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.bottomnavigation.*

abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun onStart() {
        super.onStart()
        initNavigation()
    }

    private fun initNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}