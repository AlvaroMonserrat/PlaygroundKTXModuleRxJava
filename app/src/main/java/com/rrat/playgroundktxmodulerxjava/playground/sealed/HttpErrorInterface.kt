package com.rrat.playgroundktxmodulerxjava.playground.sealed

sealed interface HttpErrorInterface
{
    object Unauthorized: HttpErrorInterface
}


fun main()
{
    val error: HttpErrorInterface = HttpErrorInterface.Unauthorized

    if(error is HttpErrorInterface.Unauthorized)
    {
        println("Error interface type Unauthorized")
    }
}