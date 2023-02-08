package com.example.astronovos.core

import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 8/02/2023
 */

abstract class UseCase<Source> {

  abstract suspend fun execute():Flow<Source>
}