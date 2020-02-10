package com.java90.movilboxtest.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.repositories.NetworkRepository
import com.java90.movilboxtest.repositories.RoomRepository
import com.java90.movilboxtest.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostMainViewModel(application: Application) : AndroidViewModel(application) {

    private val networkRepository: NetworkRepository = NetworkRepository(application)
    private val roomRepository: RoomRepository = RoomRepository(application)


    val fetchListPost = liveData(IO){
        Log.d("TAG", "Fetch liveData: " + Thread.currentThread().name)
        emit(Resource.Loading())
        try {
            emit(networkRepository.fetchDataInDatabase())
        }catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getListPost() : LiveData<List<Post>> {
        return roomRepository.loadAllPostFromDatabase()
    }

    fun deletePost(post: Post) {
        CoroutineScope(IO).launch {
            Log.d("TAG", "Delete Post: " + Thread.currentThread().name)
            roomRepository.deletePostFromDatabase(post)
        }
    }

    fun deleteAllPosts() {
        Log.d("TAG", "Delete All: " + Thread.currentThread().name)
        CoroutineScope(IO).launch {
            roomRepository.deleteAllPostsFromDatabase()
        }
    }

}
