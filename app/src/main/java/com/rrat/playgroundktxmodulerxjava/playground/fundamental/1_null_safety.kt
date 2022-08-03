package com.rrat.playgroundktxmodulerxjava.playground.fundamental

data class Engine(var fuel: Double){
}
//equals
//hashCode
//toString
//copy
//componentN

fun main()
{
    val name: String? = "John Doe"
    val cloneName = name ?: "Unknown"

    printName(name)
    printName(cloneName)

    val myEngine = Engine(3000.0)
    val myAnotherEngine = Engine(5000.0)
    myAnotherEngine.fuel = 3000.0
    val myThirdEngine = myAnotherEngine.copy(5000.0)

    println(myEngine.hashCode())
    println(myAnotherEngine.hashCode())
    println(myThirdEngine.hashCode())

    println(myEngine.fuel)
    println(myAnotherEngine.fuel)
    println(myThirdEngine.fuel)

    println(myEngine == myAnotherEngine)

    println(myEngine.component1())
}

fun printName(name: String?)
{
    val response = name?.lowercase()
    println(response)
}