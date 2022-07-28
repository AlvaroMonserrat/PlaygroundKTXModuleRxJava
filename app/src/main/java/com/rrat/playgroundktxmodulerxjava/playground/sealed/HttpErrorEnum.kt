package com.rrat.playgroundktxmodulerxjava.playground.sealed

enum class HttpErrorEnum(val code: Int) {
    Unauthorized(401),
    NotFound(404);

    fun showSpecification()
    {
        println("Http Error enum Class")
    }
}


fun main() {

    val errorEnum: HttpErrorEnum = HttpErrorEnum.NotFound
    errorEnum.showSpecification()

    HttpErrorEnum.values().forEach { println(it) }


    when(errorEnum)
    {
        HttpErrorEnum.Unauthorized ->  println("Error Not Found ${errorEnum.code}")
        HttpErrorEnum.NotFound -> println("Error Unauthorized ${errorEnum.code}")
    }

}