package com.java90.movilboxtest.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.java90.movilboxtest.R

class FavoriteFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_favorite

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorites"

    }
}