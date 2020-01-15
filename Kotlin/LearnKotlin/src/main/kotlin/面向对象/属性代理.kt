package 面向对象

import kotlin.reflect.KProperty

//一旦用 by 声明一个变量，它的 get 方法就交给代理对象操作

class Delegates {
    //在第一次方法 hello 的时候才会初始化为 “HelloWorld”
    val hello by lazy {
        "HelloWorld"
    }
    val hello2 by X()
}

class X {
    private var value: String? = null

    operator fun getValue(thisRef: Any?, propert: KProperty<*>): String {
        println("getValue：$thisRef -> ${propert.name}")
        return value ?: ""
    }

    operator fun setValue(thisRef: Any?, propert: KProperty<*>, value: String) {
        println("setValue：$thisRef -> ${propert.name} = $value")
        this.value = value
    }
}

fun main(args: Array<String>) {
    println(Delegates().hello)
    println(Delegates().hello2)
}

/*
定义方法：
-val/var <property name>:<Type> by
<expression>
 */