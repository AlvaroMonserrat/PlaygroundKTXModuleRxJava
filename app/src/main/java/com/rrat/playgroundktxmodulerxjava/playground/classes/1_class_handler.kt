package com.rrat.playgroundktxmodulerxjava.playground.classes






class ObjectGraph(){

    private var dependencies: MutableMap<Class<*>, Any> = mutableMapOf()

    init {
        val kitty: Cat = Cat("Minino")
        dependencies[Cat::class.java] = kitty
    }

    operator fun <T> get(model: Class<T>): T {
        return model.cast(dependencies[model])
    }

    fun <T : Any> putMock(clazz: Class<T>, obj: T)
    {
        dependencies[clazz] = obj
    }

}


class Cat (private val name: String){
}

fun main()
{
    val objGraph = ObjectGraph()
    val cat = objGraph[Cat::class.java]

}