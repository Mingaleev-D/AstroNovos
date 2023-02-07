package com.example.astronovos.data.koin

import android.util.Log
import com.example.astronovos.data.repository.PostRepository
import com.example.astronovos.data.repository.PostRepositoryImpl
import com.example.astronovos.data.services.AstroNovosService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

   private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"
   private const val OK_HTTP = "Ok http"

   private fun postsModule(): Module {
      return module {
         single<PostRepository> { PostRepositoryImpl(get()) }
      }
   }

   fun load() {
      loadKoinModules(postsModule() + networkModule())
   }

   private inline fun <reified T> createService(factory: Moshi, client: OkHttpClient): T {
      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(MoshiConverterFactory.create(factory))
         .client(client)
         .build()
         .create(T::class.java)
   }

   private fun networkModule(): Module {
      return module {
         single<AstroNovosService> { createService(get(), get()) }

         single {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
         }
         single {
            val interceptor = HttpLoggingInterceptor {
               Log.e(OK_HTTP, "networkModule: $it ")
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
               .addInterceptor(interceptor)
               .build()
         }
      }
   }

}