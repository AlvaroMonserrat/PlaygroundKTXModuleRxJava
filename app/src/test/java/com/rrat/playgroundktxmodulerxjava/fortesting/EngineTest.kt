package com.rrat.playgroundktxmodulerxjava.fortesting

import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Engine
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class EngineTest{

    private val engine = Engine(2000, 189, 15, false)
    @Test
    fun engineTurnsOn()= runTest{
        engine.turnOn()
        assertEquals(true, engine.isTurnedOn)
        assertEquals(95, engine.temperature)
    }

    @Test
    fun engineTurnsOff()
    {
        engine.turnOff()
        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}