package 程序结构

class People(var name: String) {
    var age: Int = 0
        get() {
            println("我想在 get 前做些什么")
            return field
        }
        set(value) {
            println("我想在 set 前做些什么")
            field = value
        }
    //如果要更改 set 访问控制权限：protected set 即可

    fun talk(word: String) {
        println("${name}说了${word}")
    }
}

class A {

}

//延迟初始化
class B {
    //var
    lateinit var str: String
    //val
    val a: A by lazy {
        println("init A")
        A()
    }
}

fun main(args: Array<String>) {
    val someOne = People("小明")
    someOne.talk("哈哈")
    val b = B()
    println(b.a)
}