package 面向对象


//默认 public, internal 模块内（module）可见
class House

class Flower

class Countyard {
    private val house: House = House()
    private val flower: Flower = Flower()
}

class ForbiddenCity {
    val houses = arrayOf(House(), House())
    val flower = arrayOf(Flower(), Flower())
}

fun main(args: Array<String>) {
    val countyard: Countyard = Countyard()
    val fc = ForbiddenCity()
    println(fc.flower)
}
/*
private
protected
internal
public
 */