package com.rrat.playgroundktxmodulerxjava.fortesting.unittests

import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Car
import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Engine
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.MainDispatcherRule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CardShould {

    private val engine: Engine = mock()
    private val car : Car

    init {
        runTest {
            whenever(engine.turnOn()).thenReturn(flow{
                emit(25)
                emit(50)
                emit(95)
            })
        }
        car =  Car(engine, 5.0)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loseFuelWhenItTurnsOn()= runTest{
        car.turnOn()
        assertEquals("Loosing fuel was not correct", 4.5, car.fuel, 0.0)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun turnOnItsEngine() = runTest{
        car.turnOn()
        verify(engine, times(1)).turnOn()
    }

}