package com.example.astronovos.ui.koin

import com.example.astronovos.ui.fragments.home.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * @author : Mingaleev D
 * @data : 6/02/2023
 */

object UiModule {

   private fun viewModelModule():Module{
      return module {
         factory { HomeViewModel(get()) }
      }
   }

   fun load(){
      loadKoinModules(viewModelModule())
   }


}