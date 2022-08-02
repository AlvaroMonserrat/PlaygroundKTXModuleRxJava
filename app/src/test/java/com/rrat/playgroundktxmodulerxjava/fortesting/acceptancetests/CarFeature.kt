package com.rrat.playgroundktxmodulerxjava.fortesting.acceptancetests

import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Car
import com.rrat.playgroundktxmodulerxjava.fortesting.outside.Engine
import com.rrat.playgroundktxmodulerxjava.fortesting.utils.MainDispatcherRule
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule

class CarFeature {

    private val engine = Engine(2000, 800, 95, false)
    private val car = Car(engine, 6.0)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun carIsLoosingFuelWhenItTurnsOn()
    {
        car.turnOn()
        assertEquals("Loosing fuel was not correct", 5.5, car.fuel, 0.0)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature() {
        car.turnOn()

        assertEquals(95, car.engine.temperature)
        assertEquals(true, car.engine.isTurnedOn)
    }


}