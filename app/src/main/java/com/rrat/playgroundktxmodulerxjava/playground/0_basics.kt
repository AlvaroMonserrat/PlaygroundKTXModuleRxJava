package com.rrat.playgroundktxmodulerxjava.playground


class Parchment(private var x: Int = 0, private var y: Int = 0){
    fun drag(x: Int, y:Int) : Int
    {
        println("Drag Parchment from (${this.x}, ${this.y})")
        this.x = x
        this.y = y
        return x
    }

    fun drop() : Int
    {
        println("Drag Parchment in ($x, $y)")
        return x
    }
}

var myNumber : Int? = null
var width = 1

fun main()
{
    //runPlayground()
}

fun letPlayground()
{
    //myNumber = 100
    println(myNumber)
    val myAnotherNumber = myNumber?.let {it+1} ?: 0
    println(myAnotherNumber)
}

fun alsoPlayground()
{
    println(getSquaredId())
    println(getSquaredId())
    println(getSquaredId())

}

fun getSquaredId() = (width * width).also {width++}


fun applyPlayground()
{
    val parchment = Parchment(10, 5).apply {
        drag(100, 100)
        drop()
    }
}

fun runPlayground()
{
    val result = Parchment(10, 5).run {
        drag(100, 100)
        drop()
        drag(100, 100).plus(drop())
    }
    println(result)
}

