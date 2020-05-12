package com.java90.movilboxtest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.models.Post

class FavoritesAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val TYPE_FOOTER = 0
    private val TYPE_ITEMS = 1

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
            differ.currentList.size+1 -> {TYPE_FOOTER}
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

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        return when(holder) {
            is FooterViewHolder -> {
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
            TODO("Not yet implemented")
        }

    }

    inner class ItemsViewHolder(itemView: View) : BaseViewHolder<Post>(itemView) {
        override fun bin(item: Post) {
            TODO("Not yet implemented")
        }
    }

}