package com.java90.movilboxtest.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.java90.movilboxtest.R
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_post

    private val args: PostFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Post"

        val post = args.post

        Glide.with(this).load(R.drawable.coffee).into(ivPost)
        tvTitle.text = post.title
        tvBody.text = post.body

        fb.setOnClickListener {
            viewModel.insertPost(post)
            Snackbar.make(view, "Post guardado exitosamente", Snackbar.LENGTH_SHORT).show()
        }
    }
}