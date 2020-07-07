package com.java90.movilboxtest.presentation.view.fragments.postlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java90.movilboxtest.domain.models.Post
import com.java90.movilboxtest.domain.usecases.DataNetworkUseCase
import com.java90.movilboxtest.domain.util.Resource
import kotlinx.coroutines.launch


class PostsListViewModel @ViewModelInject constructor(
    private val dataNetworkUseCase: DataNetworkUseCase) : ViewModel() {

    val dataResponse: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    fun getAllPosts() = viewModelScope.launch{
        dataResponse.postValue(Resource.Loading())
        try {
            val response = dataNetworkUseCase.getDataNetwork()
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    dataResponse.postValue(Resource.Success(resultResponse))
                }
            }
        } catch (e: Exception) {
            dataResponse.postValue(Resource.Failure(e.message.toString()))
        }
    }
}
