package com.example.astronovos

import android.app.Application
import com.example.astronovos.data.koin.DataModule
import com.example.astronovos.ui.koin.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author : Mingaleev D
 * @data : 6/02/2023
 */

class MyApp:Application() {

   override fun onCreate() {
      super.onCreate()

      startKoin {
         androidContext(this@MyApp)
      }

      UiModule.load()
      DataModule.load()
   }
}