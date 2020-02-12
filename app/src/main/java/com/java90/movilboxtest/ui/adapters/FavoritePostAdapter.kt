package com.java90.movilboxtest.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.ui.view.DetailPostActivity
import com.java90.movilboxtest.utils.OnFavoriteClickListener
import kotlinx.android.synthetic.main.row_item_post.view.*
import java.lang.reflect.Array

class FavoritePostAdapter(private val context: Context) :
    RecyclerView.Adapter<FavoritePostAdapter.FavoriteViewHolder>() {

    private var listFavorites = emptyList<Post>()

    internal fun setListFavorites (posts: List<Post>) {
        listFavorites = posts
        notifyDataSetChanged()
    }

    fun getPostAt(position: Int) : Post {
        return listFavorites[position]
    }

    fun deleteItem(position: Int) {
        listFavorites.toMutableList().removeAt(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_post, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = listFavorites.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val post = listFavorites[position]
        holder.bin(post)
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(post: Post) {
            itemView.textView_title.text = post.title

            itemView.imageButton_start.background = ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp)

            itemView.cardView_color.setOnClickListener {
                val intent = Intent(itemView.context, DetailPostActivity::class.java)
                intent.putExtra(PostMainAdapter.KEY_USER_ID, post.userId.toString())
                intent.putExtra(PostMainAdapter.KEY_ID, post.id.toString())
                intent.putExtra(PostMainAdapter.KEY_TITLE, post.title)
                intent.putExtra(PostMainAdapter.KEY_BODY, post.body)
                itemView.context.startActivity(intent)
            }
        }
    }
}