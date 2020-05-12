package com.java90.movilboxtest.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.java90.movilboxtest.repositories.PostRepository

class PostsViewModelFactory(private val postsRepository: PostRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(postsRepository) as T
    }
}