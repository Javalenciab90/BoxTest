package com.java90.movilboxtest.ui.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.java90.movilboxtest.R
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.ui.base.BaseActivity
import com.java90.movilboxtest.ui.adapters.FavoritePostAdapter
import com.java90.movilboxtest.ui.adapters.PostMainAdapter
import com.java90.movilboxtest.ui.viewmodel.FavoriteViewModel
import com.java90.movilboxtest.utils.OnFavoriteClickListener
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_post_main.*

class FavoritesActivity : BaseActivity() {

    private val viewModelFavorite by lazy {
        ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    private lateinit var adapterFavoritePost: FavoritePostAdapter
    private lateinit var colorDrawableBackground: ColorDrawable
    private lateinit var deleteIcon : Drawable

    override fun getViewID(): Int = R.layout.activity_favorites
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolBarFavorite)
        setTitleActionBar("Favorites Posts")

        adapterFavoritePost = FavoritePostAdapter(this)
        recyclerViewFavorites.layoutManager = LinearLayoutManager(this)
        recyclerViewFavorites.adapter = adapterFavoritePost
        recyclerViewFavorites.setHasFixedSize(true)
        recyclerViewFavorites.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        colorDrawableBackground = ColorDrawable(Color.parseColor("#E45902"))
        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete_forever_black_24dp)!!

        viewModelFavorite.getListPost().observe(this,
            Observer {
                adapterFavoritePost.setListFavorites(it)
            }
        )
    }

    override fun onResume() {
        super.onResume()

        deleteItemFromRecycler()
    }

    private fun deleteItemFromRecycler() {
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView,
                                    viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val post = adapterFavoritePost.getPostAt(position)
                    viewModelFavorite.deletePost(post)
                    adapterFavoritePost.deleteItem(position)
                }

                override fun onChildDraw(c: Canvas, recyclerView: RecyclerView,
                                         viewHolder: RecyclerView.ViewHolder,
                                         dX: Float, dY: Float, actionState: Int,
                                         isCurrentlyActive: Boolean) {

                    val itemView = viewHolder.itemView
                    val iconMarginVertical = (viewHolder.itemView.height - deleteIcon.intrinsicHeight) / 2

                    if(dX > 0){
                        colorDrawableBackground.setBounds(itemView.left,
                            itemView.top, dX.toInt(),
                            itemView.bottom)
                        deleteIcon.setBounds(itemView.left + iconMarginVertical,
                            itemView.top + iconMarginVertical,
                            itemView.left + iconMarginVertical + deleteIcon.minimumWidth,
                            itemView.bottom - iconMarginVertical)
                    }else {
                        colorDrawableBackground.setBounds(itemView.right + dX.toInt(),
                            itemView.top,
                            itemView.right,
                            itemView.bottom)
                        deleteIcon.setBounds(itemView.right - iconMarginVertical - deleteIcon.intrinsicWidth,
                            itemView.top + iconMarginVertical,
                            itemView.right - iconMarginVertical,
                            itemView.bottom - iconMarginVertical)
                        deleteIcon.level = 0
                    }
                    colorDrawableBackground.draw(c)
                    c.save()

                    if(dX > 0)
                        c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    else
                        c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                    deleteIcon.draw(c)
                    c.restore()



                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewFavorites)
    }
}
