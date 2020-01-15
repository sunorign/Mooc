package 面向对象

//避免使用过多 XXUtils,可以给 String 这样的类进行二次加工

/*
扩展方法
 */
//eg:重复输出

fun String.multiply(int: Int): String {
    val stringBuilder = StringBuilder()
    for (i in 0 until int) {
        stringBuilder.append(this)
    }
    return stringBuilder.toString()
}

//运算符
operator fun String.times(int: Int): String {
    val stringBuilder = StringBuilder()
    for (i in 0 until int) {
        stringBuilder.append(this)
    }
    return stringBuilder.toString()
}

/*
扩展属性
 */
val String.a: String
    get() = "hello"

fun main(args: Array<String>) {
    println("abc".multiply(10))
    println("abc" * 12)
    println("abc".a)
}
/*
定义：为现有的类添加方法属性

-fun X.y():z{...}

-val X.m 注意扩展属性不能初始化，类似接口属性

Java 调用扩展成员类似调用静态方法
 */