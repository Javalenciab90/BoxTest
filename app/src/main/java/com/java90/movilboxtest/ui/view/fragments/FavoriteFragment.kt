package com.java90.movilboxtest.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.java90.movilboxtest.R
import com.java90.movilboxtest.ui.adapters.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_favorite

    lateinit var favoriteAdapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorites"

        initRecyclerView()

        viewModel.getFavoritesPosts().observe(viewLifecycleOwner,
            Observer { posts ->
                favoriteAdapter.differ.submitList(posts)
            }
        )

        favoriteAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("post", it)
            }
            findNavController().navigate(
                R.id.action_favoriteFragment_to_postFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val post = favoriteAdapter.differ.currentList[position]
                viewModel.deletePost(post)
                Snackbar.make(view, "Post Eliminado", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.insertPost(post)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvFavorites)
        }
    }

    private fun initRecyclerView() {
        favoriteAdapter = FavoritesAdapter()
        rvFavorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteAdapter
        }
    }
}