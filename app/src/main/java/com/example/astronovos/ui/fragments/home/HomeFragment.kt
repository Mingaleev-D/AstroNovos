package com.example.astronovos.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astronovos.databinding.FragmentHomeBinding
import com.example.astronovos.ui.adapter.PostListAdapter
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
      initRecyclerView()
      return binding.root
   }

   private fun initRecyclerView() {

      val adapter = PostListAdapter()
      binding.homeRv.adapter = adapter

      viewModel.listPost.observe(viewLifecycleOwner) {
         adapter.submitList(it)
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