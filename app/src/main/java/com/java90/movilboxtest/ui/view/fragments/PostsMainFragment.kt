package com.java90.movilboxtest.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90.movilboxtest.R
import com.java90.movilboxtest.ui.adapters.PostsAdapter
import com.java90.movilboxtest.utils.Resource
import kotlinx.android.synthetic.main.fragment_posts_main.*

class PostsMainFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_posts_main

    lateinit var postAdapter: PostsAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "All Posts"

        setHasOptionsMenu(true)
        navController = Navigation.findNavController(view)

        initRecyclerView()
        viewModel.getAllPosts()
        viewModel.allPosts.observe(viewLifecycleOwner,
            Observer { response ->
                when(response) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let {
                            postAdapter.differ.submitList(it)
                        }
                    }
                    is Resource.Failure -> {
                        hideProgressBar()
                        showToast("Fallo en la conexión")
                    }
                }
            }
        )

        postAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("post", it)
            }
            findNavController().navigate(
                R.id.action_postsMainFragment_to_postFragment,
                bundle
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bar_favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favoritesPosts -> {
                navController.navigate(R.id.action_postsMainFragment_to_favoriteFragment)
                Toast.makeText(activity, "Favorites Posts", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initRecyclerView() {
        postAdapter = PostsAdapter(viewModel)
        rvPostMain.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message ,Toast.LENGTH_LONG).show()
    }
}