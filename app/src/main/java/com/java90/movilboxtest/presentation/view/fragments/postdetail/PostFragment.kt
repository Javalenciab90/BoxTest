package com.java90.movilboxtest.presentation.view.fragments.postdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.java90.movilboxtest.R
import com.java90.movilboxtest.presentation.view.fragments.BaseFragment
import com.java90.movilboxtest.presentation.view.fragments.postfavorite.FavoriteViewModel
import com.java90.movilboxtest.presentation.view.fragments.postlist.PostsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post.*

@AndroidEntryPoint
class PostFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_post

    private val viewModel : FavoriteViewModel by viewModels()

    private val args: PostFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Post"


        val post = args.post

        Glide.with(this).load(R.drawable.coffee).into(ivPost)
        tvTitle.text = post.title
        tvBody.text = post.body

        fb.setOnClickListener {
            viewModel.insert(post)
            Snackbar.make(view, "Post guardado exitosamente", Snackbar.LENGTH_SHORT).show()
        }

    }
}