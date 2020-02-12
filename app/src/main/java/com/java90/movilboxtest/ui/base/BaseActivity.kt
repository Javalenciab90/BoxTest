package com.java90.movilboxtest.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_post_main.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewID())
    }

    abstract fun getViewID(): Int

    fun setTitleActionBar(titleActionBar: String) {
        supportActionBar!!.title = titleActionBar
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun showProgressBar() {
        progress_circular.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progress_circular.visibility = View.GONE
    }

    fun showErrorProgressBar() {
        progress_circular.visibility = View.VISIBLE
        textView_error.visibility = View.VISIBLE
    }
}