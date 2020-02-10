package com.java90.movilboxtest.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.ui.base.BaseActivity
import com.java90.movilboxtest.ui.viewmodel.PostMainViewModel
import com.java90.movilboxtest.vo.Resource
import kotlinx.android.synthetic.main.activity_post_main.*


class PostMainActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(PostMainViewModel::class.java)
    }

    lateinit var adapter: PostMainAdapter

    override fun getViewID(): Int = R.layout.activity_post_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleActionBar("Post List")

        adapter = PostMainAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchDataFromNetwork()
        viewModel.getListPost().observe(this,
            Observer {
                adapter.setListPost(it)
            }
        )

    }

    private fun fetchDataFromNetwork() {
        viewModel.fetchListPost.observe(this,
            Observer {
                when (it) {
                    is Resource.Loading -> { showProgressBar() }
                    is Resource.Success -> { hideProgressBar() }
                    is Resource.Failure -> {
                        showToast("Error: Connection problem. Check your Internet")
                        hideProgressBar()
                    }
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.post_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refreshAllPosts -> {
                return true
            }
            R.id.deleteAllPosts -> {
                adapter.deleteAllItems()
                viewModel.deleteAllPosts()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        deleteItemFromRecycler()
    }

    private fun deleteItemFromRecycler() {
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView,
                                    viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean {
                    viewHolder.itemView.setBackgroundColor(Color.CYAN)
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder,direction: Int) {
                    val position = viewHolder.adapterPosition
                    val post = adapter.getPostAt(position)
                    viewModel.deletePost(post)
                    adapter.deleteItem(position)
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
