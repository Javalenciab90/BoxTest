package com.java90.movilboxtest.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.java90.movilboxtest.models.Post
import com.java90.movilboxtest.repositories.PostRepository
import com.java90.movilboxtest.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(private val postsRepository: PostRepository) : ViewModel() {

    val allPosts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    fun getAllPosts() = viewModelScope.launch {
        allPosts.postValue(Resource.Loading())
        try {
            val response = postsRepository.getAllPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    allPosts.postValue(Resource.Success(it))
                }
            }
        }catch (e: Exception) {
            allPosts.postValue(Resource.Failure(e.message.toString()))
        }
    }

    fun getFavoritesPosts() = postsRepository.getAllFavoritesPosts()

    fun insertPost(post: Post) = viewModelScope.launch {
        postsRepository.insertPost(post)
    }

    fun isFavoritePost(id: Int) = viewModelScope.launch {
       postsRepository.isFavorite(id)
    }


    fun deletePost(post: Post) = viewModelScope.launch {
        postsRepository.deletePost(post)
    }
}
