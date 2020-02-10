package com.java90.movilboxtest.ui

import android.os.Bundle
import com.java90.movilboxtest.R
import com.java90.movilboxtest.ui.PostMainAdapter.Companion.KEY_BODY
import com.java90.movilboxtest.ui.PostMainAdapter.Companion.KEY_ID
import com.java90.movilboxtest.ui.PostMainAdapter.Companion.KEY_TITLE
import com.java90.movilboxtest.ui.PostMainAdapter.Companion.KEY_USER_ID
import com.java90.movilboxtest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_post.*

class DetailPostActivity : BaseActivity() {

    override fun getViewID(): Int = R.layout.activity_detail_post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(KEY_USER_ID)!!.toString()
        val id = intent.getStringExtra(KEY_ID)!!.toString()
        val title =  intent.getStringExtra(KEY_TITLE)!!.toString()
        val body = intent.getStringExtra(KEY_BODY)!!.toString()

        textView_userId.text = userId
        textView_id.text = id
        textView_title.text = title
        textView_body.text = body
    }
}
