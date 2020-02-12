package com.java90.movilboxtest.ui.view

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.java90.movilboxtest.R
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.ui.adapters.PostMainAdapter
import com.java90.movilboxtest.ui.base.BaseActivity
import com.java90.movilboxtest.ui.viewmodel.PostMainViewModel
import com.java90.movilboxtest.utils.OnFavoriteClickListener
import com.java90.movilboxtest.vo.Resource
import kotlinx.android.synthetic.main.activity_post_main.*

class PostMainActivity : BaseActivity(), OnFavoriteClickListener {

    private val viewModelMain by lazy {
        ViewModelProvider(this).get(PostMainViewModel::class.java)
    }

    private lateinit var adapterMain: PostMainAdapter

    override fun getViewID(): Int = R.layout.activity_post_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolBar)
        setTitleActionBar("Post List")

        adapterMain = PostMainAdapter(this, this)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = adapterMain

        fetchDataFromNetwork()
    }

    private fun fetchDataFromNetwork() {
        viewModelMain.fetchListPost.observe(this,
            Observer {
                when (it) {
                    is Resource.Loading -> { showProgressBar() }
                    is Resource.Success -> {
                        adapterMain.setListPost(it.data.body()!!.toList())
                        hideProgressBar() }
                    is Resource.Failure -> {
                        showToast("Error: Connection problem. Check your Internet")
                        showErrorProgressBar()
                    }
                }
            }
        )
    }

    override fun onClickFavorite(post: Post) {
        viewModelMain.insertToFavorites(post)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.post_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favoritesPosts -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
