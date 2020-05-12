package com.java90.movilboxtest.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.java90.movilboxtest.R
import com.java90.movilboxtest.repositories.PostRepository

class PostsActivity : AppCompatActivity() {

    lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val repository = PostRepository()
        val viewModelFactory = PostsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PostsViewModel::class.java)

    }
}