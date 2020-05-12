package com.java90.movilboxtest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.models.Post
import kotlinx.android.synthetic.main.row_item_post.view.*

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.bin(post)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bin(post: Post) {
            itemView.apply {
                textView_title.text = post.title

                setOnClickListener {
                    onItemCLickListener?.let {
                        it(post)
                    }
                }
            }
        }
    }

    private var onItemCLickListener: ((Post) -> Unit)? = null
    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemCLickListener = listener
    }
}