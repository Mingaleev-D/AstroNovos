package com.example.astronovos

import com.example.astronovos.data.repository.PostRepository
import com.example.astronovos.data.repository.PostRepositoryImpl
import com.example.astronovos.data.services.AstroNovosService
import com.example.astronovos.domain.GetLatestPostsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author : Mingaleev D
 * @data : 8/02/2023
 */

fun configureDomainModuleForTest() = module {
   factory<GetLatestPostsUseCase> { GetLatestPostsUseCase(get()) }
}

fun configureDataModuleForTest(baseUrl:String) = module {

   single {
      val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

      Retrofit.Builder()
         .baseUrl(baseUrl)
         .addConverterFactory(MoshiConverterFactory.create(factory))
         .build()
         .create(AstroNovosService::class.java)
   }

   single<PostRepository> { PostRepositoryImpl(get()) }
}