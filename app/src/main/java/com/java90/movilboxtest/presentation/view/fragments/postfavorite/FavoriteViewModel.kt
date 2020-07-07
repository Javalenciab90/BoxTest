package com.java90.movilboxtest.presentation.view.fragments.postfavorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java90.movilboxtest.domain.models.Post
import com.java90.movilboxtest.domain.usecases.DataLocalUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val dataLocalUseCase: DataLocalUseCase) : ViewModel() {

    fun get() = dataLocalUseCase.getLocalData()

    fun insert(post: Post) = viewModelScope.launch {
        dataLocalUseCase.insert(post)
    }

    fun delete(post: Post) = viewModelScope.launch {
        dataLocalUseCase.delete(post)
    }
}