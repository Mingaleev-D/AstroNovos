package com.example.astronovos

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * @author : Mingaleev D
 * @data : 8/02/2023
 */


const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

fun configureTestAppComponent() = startKoin {
   loadKoinModules(configureDataModuleForTest(BASE_URL) + configureDomainModuleForTest())
}