package com.example.astronovos.data.services

import com.example.astronovos.data.model.Post
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 7/02/2023
 */

interface AstroNovosService {

   @GET("articles")
   suspend fun listPosts():List<Post>
}