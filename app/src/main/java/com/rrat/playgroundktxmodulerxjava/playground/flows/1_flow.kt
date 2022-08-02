package com.rrat.playgroundktxmodulerxjava.playground.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main()= runBlocking {
    val myFlow = getSpecialNumber()
    println("Create Flow")

    myFlow.take(5).collect{
        println("Observe value was $it")
    }
}

fun getSpecialNumber() : Flow<Int>
{
    return flow{
        repeat(5)
        {
            delay(1000)
            emit(100)
        }
    }
}