package com.example.astronovos.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astronovos.core.State
import com.example.astronovos.data.model.Post
import com.example.astronovos.databinding.FragmentHomeBinding
import com.example.astronovos.ui.adapter.PostListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


   private val viewModel: HomeViewModel by viewModel()
   private val binding: FragmentHomeBinding by lazy {
      FragmentHomeBinding.inflate(layoutInflater)
   }

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View? {

      initBinding()
      initSnackBar()
      initRecyclerView()
      return binding.root
   }

   private fun initSnackBar() {
      viewModel.snackBar.observe(viewLifecycleOwner){
         it?.let { errorMessage ->
            Snackbar.make(binding.root,errorMessage,Snackbar.LENGTH_LONG).show()

            viewModel.onSnackBarShown()
         }
      }
   }

   private fun initRecyclerView() {

      val adapter = PostListAdapter()
      binding.homeRv.adapter = adapter

      viewModel.listPost.observe(viewLifecycleOwner) { state ->
         when(state){
            State.Loading    -> {
               viewModel.showProgressBar()
            }
            is State.Success -> {
               viewModel.hideProgressBar()
               adapter.submitList(state.result as List<Post>)
            }
            is State.Error   -> {
               viewModel.hideProgressBar()
            }
         }

      }

   }

   private fun initBinding() {
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
   }

   companion object {
      fun newInstance() = HomeFragment()
   }

}