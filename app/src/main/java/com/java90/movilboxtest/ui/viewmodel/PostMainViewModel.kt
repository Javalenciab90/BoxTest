package com.java90.movilboxtest.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.java90.movilboxtest.db.Post
import com.java90.movilboxtest.repositories.NetworkRepository
import com.java90.movilboxtest.repositories.RoomRepository
import com.java90.movilboxtest.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    fun insertToFavorites(post:Post) {
        CoroutineScope(IO).launch {
            roomRepository.insertPostInDatabase(post)
        }
    }

}
