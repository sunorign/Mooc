package 面向对象

//抽象类可以不写 open （因为抽象类生来被继承）,覆写方法必须加上 override

/**
 * 属性被复写
 */
abstract class Person(open val profession: String) {
    abstract fun work();
}

class Coder(profession: String) : Person(profession) {
    override val profession: String
        get() = "码农"

    override fun work() {
        println("我是${profession}，在写代码")
    }
}

fun main(args: Array<String>) {
    val person = Coder("开发者")
    person.work()

    //接口代理
    val seniorManager = SeniorManager(CarDriver(), Clerk())
    seniorManager.drive()
    seniorManager.write()
}

/*
by 接口代理
 */
class SeniorManager(val driver: Driver, val writer: Writer) : Driver by driver, Writer by writer

class CarDriver() : Driver {
    override fun drive() {
        println("开轿车")
    }
}

class Clerk : Writer {
    override fun write() {
        println("写报告")
    }

}

interface Driver {
    fun drive()
}

interface Writer {
    fun write()
}

//两个接口有相同的方法，一个类继承它们出现冲突的情况
interface B {
    fun x(): Int = 1
}

interface C {
    fun x(): Int = 0
}

class D : B, C {
    var i: Int = 0
    override fun x(): Int {
        if (i > 0) {
            return i
        } else if (i < -100) {
            return super<B>.x()
        } else {
            return super<C>.x()
        }
    }
}