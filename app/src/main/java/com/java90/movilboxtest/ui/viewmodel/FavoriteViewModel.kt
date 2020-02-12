package com.java90.movilboxtest.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.repositories.RoomRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val roomRepository: RoomRepository = RoomRepository(application)

    fun getListPost() : LiveData<List<Post>> {
        return roomRepository.loadAllPostFromDatabase()
    }

    fun deletePost(post: Post) = viewModelScope.launch {
            Log.d("TAG", "Delete Post: " + Thread.currentThread().name)
            roomRepository.deletePostFromDatabase(post)
    }
}
