package com.java90.movilboxtest.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.java90.movilboxtest.R

class PostFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_post

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Post"

    }
}