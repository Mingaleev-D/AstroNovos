package com.example.astronovos.data.koin

import com.example.astronovos.data.repository.MockAPIService
import com.example.astronovos.data.repository.PostRepository
import com.example.astronovos.data.repository.PostRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

   private fun postsModule():Module{
      return module {
         single<PostRepository> { PostRepositoryImpl(MockAPIService) }
      }
   }

   fun load(){
      loadKoinModules(postsModule())
   }
}