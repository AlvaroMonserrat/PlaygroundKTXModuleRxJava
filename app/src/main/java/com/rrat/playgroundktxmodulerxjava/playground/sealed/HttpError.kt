package com.rrat.playgroundktxmodulerxjava.playground.sealed


sealed class HttpError(val code: Int){
    data class Unauthorized(val reason: String): HttpError(401)
    object NotFound: HttpError(404)

    fun showSpecification()
    {
        println("Http Error sealed Class")
    }
}


fun main()
{
    val errorOne: HttpError = HttpError.Unauthorized("Life is hard")
    val errorTwo: HttpError = HttpError.Unauthorized("Nobody knows")
    errorOne.showSpecification()


    when(errorOne)
    {
        HttpError.NotFound -> println("Error Not Found : ${errorOne.code}")
        HttpError.Unauthorized("Life is hard") -> println("Error Unauthorized, life is hard :( ${errorOne.code}")
        else -> {println("Error Unknown")}
    }

    when(errorTwo)
    {
        is HttpError.NotFound -> println("Error Not Found : ${errorOne.code}")
        is HttpError.Unauthorized -> println("Error Unauthorized ${errorOne.code}")
    }
}