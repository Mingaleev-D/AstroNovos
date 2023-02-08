package com.example.astronovos.ui.fragments.home

import androidx.lifecycle.*
import com.example.astronovos.core.RemoteException
import com.example.astronovos.core.State
import com.example.astronovos.data.model.Post
import com.example.astronovos.domain.GetLatestPostsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLatestPostsUseCase: GetLatestPostsUseCase
) : ViewModel() {

   private val _progressBarVisible = MutableLiveData<Boolean>()
   val progressBarVisible: LiveData<Boolean> get() = _progressBarVisible

   fun showProgressBar(){
      _progressBarVisible.value = true
   }
   fun hideProgressBar(){
      _progressBarVisible.value = false
   }

   private val _snackBar = MutableLiveData<String?>(null)
   val snackBar :LiveData<String?> get() = _snackBar

   fun onSnackBarShown(){
      _snackBar.value = null
   }

   private val _listPost = MutableLiveData<State<List<Post>>>()
   val listPost: LiveData<State<List<Post>>>
      get() = _listPost

   init {
      fetchPosts()
   }

   private fun fetchPosts() {
      viewModelScope.launch {
         getLatestPostsUseCase.execute()
            .onStart {
               _listPost.postValue(State.Loading)
            }
            .catch {
               val exeption = RemoteException("Unable to connect")
               _listPost.postValue(State.Error(exeption))
               _snackBar.value = exeption.message
            }
            .collect { listPost ->
            _listPost.postValue(State.Success(listPost))
         }
      }
   }

   val textHey = Transformations.map(listPost){ state ->
      when(state){
         State.Loading  -> {
            "Loading latest novas ... "
         }
         is State.Error -> {
            "Houston, we have a problem("
         }
         else           -> {
            " "
         }
      }

   }

}