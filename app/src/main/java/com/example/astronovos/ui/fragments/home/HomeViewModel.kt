package com.example.astronovos.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronovos.data.model.Post
import com.example.astronovos.data.repository.MockAPIService
import com.example.astronovos.data.repository.PostRepository
import com.example.astronovos.data.repository.PostRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

   val repository : PostRepository = PostRepositoryImpl(MockAPIService)

   private val _listPost = MutableLiveData<List<Post>>()
   val listPost: LiveData<List<Post>>
      get() = _listPost

   init {
      fetchPosts()
   }

   private fun fetchPosts() {
      viewModelScope.launch {
         repository.listPosts().collect {
            _listPost.value = it
         }
      }
   }

}