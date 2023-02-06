package com.example.astronovos.data.repository

import com.example.astronovos.data.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 6/02/2023
 */

interface PostRepository {

   suspend fun listPosts():Flow<List<Post>>
}