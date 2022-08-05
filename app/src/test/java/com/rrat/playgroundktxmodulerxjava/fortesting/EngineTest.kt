package com.rrat.playgroundktxmodulerxjava.fortesting

import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Engine
import junit.framework.Assert
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class EngineTest{

    private val engine = Engine(2000, 189, 15, false)

    @Test
    fun engineTurnsOn()= runTest{
        val flow = engine.turnOn()

        val actual = flow.toList()


        assertEquals(true, engine.isTurnedOn)
        Assert.assertEquals(listOf(25, 50, 95), actual)

    }

    @Test
    fun engineTurnsOff()
    {
        engine.turnOff()
        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}