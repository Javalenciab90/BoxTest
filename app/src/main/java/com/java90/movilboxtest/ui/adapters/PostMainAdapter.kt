package com.java90.movilboxtest.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.ui.view.DetailPostActivity
import com.java90.movilboxtest.utils.OnFavoriteClickListener
import kotlinx.android.synthetic.main.row_item_post.view.*


class PostMainAdapter(private val context: Context, listener: OnFavoriteClickListener) :
    RecyclerView.Adapter<PostMainAdapter.ViewHolder>(){

    val onClickListener : OnFavoriteClickListener = listener

    private var listPost : MutableList<Post> = mutableListOf()

    internal fun setListPost (posts: List<Post>) {
        listPost.addAll(posts)
        notifyDataSetChanged()
    }

    fun deleteAllItems() {
        listPost.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listPost[position]
        holder.bin(post)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(post: Post) {
            itemView.textView_title.text = post.title

            itemView.cardView_color.setOnClickListener {
                val intent = Intent(itemView.context, DetailPostActivity::class.java)
                intent.putExtra(KEY_USER_ID, post.userId.toString())
                intent.putExtra(KEY_ID, post.id.toString())
                intent.putExtra(KEY_TITLE, post.title)
                intent.putExtra(KEY_BODY, post.body)
                itemView.context.startActivity(intent)
            }

            itemView.imageButton_start.setOnClickListener {
                Toast.makeText(context, "Item add to Favorites", Toast.LENGTH_SHORT).show()
                onClickListener.onClickFavorite(post)
            }
        }
    }
    companion object {
        const val KEY_USER_ID = "KEY_USER_ID"
        const val KEY_ID = "KEY_ID"
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_BODY = "KEY_BODY"
    }
}







