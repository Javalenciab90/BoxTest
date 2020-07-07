package com.java90.movilboxtest.presentation.view.fragments.postfavorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.java90.movilboxtest.R
import com.java90.movilboxtest.presentation.adapters.FavoritesAdapter
import com.java90.movilboxtest.presentation.view.fragments.BaseFragment
import com.java90.movilboxtest.presentation.view.fragments.postlist.PostsListViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_favorite

    private val viewModel: FavoriteViewModel by viewModels()

    lateinit var favoriteAdapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorites"

        initRecyclerView()

        viewModel.get().observe(viewLifecycleOwner,
            Observer{
                favoriteAdapter.differ.submitList(it)
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
                viewModel.delete(post)
                Snackbar.make(view, "Post Eliminado", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.insert(post)
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