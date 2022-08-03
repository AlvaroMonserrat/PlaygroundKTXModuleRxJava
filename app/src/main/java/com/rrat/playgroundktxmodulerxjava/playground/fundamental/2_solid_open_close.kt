package com.rrat.playgroundktxmodulerxjava.playground.fundamental



//S: Single Responsibility Principle
//Each class should have its own single responsibility

//O: Open/Closed
//Entities should be open for extension, but closed for modification

/*First Example: Violate Principle*/
class Zoo(private val animals: Array<String>)
{
    fun feedAnimals(): Unit{
        for (animal in animals)
        {
            when(animal)
            {
                "duck" -> println("Feeding the $animal which has winds")
                "cat" -> println("Feeding the $animal which can walk")
                else-> println("Feeding the $animal unknown")
            }
        }
    }
}
// Everytime that I need to add a new animal, I have to edit feedAnimals method.
// So, it is not closed for modification

// Solution
interface Animal{
    fun feed()
}

abstract class ZooSolid(private val animals: Array<Animal>)
{
    fun feedAnimals(){
        for(animal in animals) animal.feed()
    }
}

class Duck: Animal{
    override fun feed() {
        println("Feeding the ${this.javaClass.simpleName} which has winds")
    }
}

class Cat: Animal{
    override fun feed() {
        println("Feeding the ${this.javaClass.simpleName} which can walk")
    }
}

//In this case, everytime that I want another animal, I just have to create another class and
// I don't have to edit neither the parent class ZooSolid nor Animal Interface


/*Second Example: Violate Principle*/
class Rectangle(val length: Double, val height: Double)
{
}

class Circle(val radius: Double){
}
class AreaTotalFactory{
    fun calculateAreaTotal(shapes: ArrayList<Any>): Double
    {
        var area = 0.0
        for(shape in shapes)
        {
            when(shape)
            {
                is Rectangle->area += shape.height * shape.length
                is Circle->area += (shape.radius * shape.radius)*Math.PI
                else-> area+= 0
            }
        }
        return area
    }
}

interface Shape
{
    fun getArea(): Double
}

// Solution
class AreaTotalFactorySolid{
    fun calculateAreaTotal(shapes: ArrayList<Shape>): Double
    {
        var area = 0.0
        for(shape in shapes)
        {
            area += shape.getArea()
        }
        return area
    }
}

class RectangleSolid(val length: Double, val height: Double): Shape{
    override fun getArea(): Double {
        return  length * height
    }

}

class CircleSolid(val radius: Double): Shape{
    override fun getArea(): Double {
        return  (radius * radius) * Math.PI
    }

}

