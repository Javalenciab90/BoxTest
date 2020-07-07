package com.java90.movilboxtest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.java90.movilboxtest.R
import com.java90.movilboxtest.domain.usecases.DataNetworkUseCase
import com.java90.movilboxtest.framework.db.PostDatabase
import com.java90.movilboxtest.presentation.view.fragments.postlist.PostsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

    }
}