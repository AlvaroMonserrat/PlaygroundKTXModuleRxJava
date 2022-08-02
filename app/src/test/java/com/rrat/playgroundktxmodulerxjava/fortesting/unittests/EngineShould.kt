package com.rrat.playgroundktxmodulerxjava.fortesting.unittests

import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Engine
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EngineShould {

    private val engine = Engine()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun turnOn()= runTest{
        engine.turnOn()
        assertTrue(engine.isTurnedOn)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun riseTheTemperatureGraduallyWhenItTurnsOn()= runTest{
        val flow = engine.turnOn()

        val actual = flow.toList()

        assertEquals(listOf(25, 50, 95), actual)
    }
}