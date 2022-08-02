package com.rrat.playgroundktxmodulerxjava.fortesting.outside

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Engine(
    val cc: Int = 2000,
    val horsePower: Int = 200,
    var temperature: Int = 15,
    var isTurnedOn: Boolean = false

)
{
    suspend fun turnOn(): Flow<Int> {
        isTurnedOn = true

        return flow {
            delay(2000)
            temperature = 25
            emit(temperature)

            delay(2000)
            temperature = 50
            emit(temperature)

            delay(2000)
            temperature = 95
            emit(temperature)
            Log.i("TDD", "Engine has turned on.")
        }
    }

    fun turnOff(){
        isTurnedOn = false
        temperature = 15
    }

}

