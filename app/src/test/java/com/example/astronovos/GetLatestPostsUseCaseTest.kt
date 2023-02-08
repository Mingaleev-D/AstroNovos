package com.example.astronovos

import com.example.astronovos.domain.GetLatestPostsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull

/**
 * @author : Mingaleev D
 * @data : 8/02/2023
 */

class GetLatestPostsUseCaseTest:KoinTest {

   val getLatestPostsUseCase:GetLatestPostsUseCase by inject()

   companion object{

      @BeforeClass
      @JvmStatic
      fun setup(){
         configureTestAppComponent()
      }

      @AfterClass
      fun tearSown(){
         stopKoin()
      }
   }

   @Test
   fun dev_ResultNull_and_ConnectOnRepository(){
      runBlocking {
         val result = getLatestPostsUseCase.execute()

         assertNotNull(result)
      }
   }
}