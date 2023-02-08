package com.example.astronovos.domain

import com.example.astronovos.core.UseCase
import com.example.astronovos.data.model.Post
import com.example.astronovos.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 8/02/2023
 */

class GetLatestPostsUseCase(
    private val repository:PostRepository
) :UseCase<List<Post>>(){

   override suspend fun execute(): Flow<List<Post>> {
      return repository.listPosts()
   }
}