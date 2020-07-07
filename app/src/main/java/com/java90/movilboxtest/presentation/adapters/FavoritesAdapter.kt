package com.java90.movilboxtest.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.domain.models.Post
import kotlinx.android.synthetic.main.row_item_post.view.*

class FavoritesAdapter() : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val TYPE_FOOTER = 1
    private val TYPE_ITEMS = 0

    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            in 0 until differ.currentList.size -> {TYPE_ITEMS}
            differ.currentList.size -> {TYPE_FOOTER}
            else -> {TYPE_ITEMS}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
                FooterViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_post, parent, false)
                ItemsViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size+1

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        return when(holder) {
            is FooterViewHolder -> {
                Log.d("TAG", "Recycle Position: $position")
                holder.bin("MyFooter")
            }
            is ItemsViewHolder -> {
                val post = differ.currentList[position]
                holder.bin(post)
            }
            else -> throw IllegalArgumentException("Invalid ViewHolder")
        }
    }

    inner class FooterViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
        override fun bin(item: String) {
            itemView.apply {
                setOnClickListener {

                    }
                }
            }
        }

    inner class ItemsViewHolder(itemView: View) : BaseViewHolder<Post>(itemView) {
        override fun bin(item: Post) {
            itemView.apply {
                textView_title.text = item.title
            }
        }
    }

    private var onItemClickListener : ((Post) -> Unit)?= null
    fun setOnClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }

}