package 程序结构

fun main(args: Array<String>) {
    try {
        val arg1 = "str".toInt();
        val arg2 = 2
        println("$arg1 + $arg2 = ${sum_1(arg1, arg2)}")
    } catch (e: NumberFormatException) {
        println("捕获异常：${e.message}")
    } finally {
        println("最后都会执行")
    }
    //try 也是表达式,类似if else 和 when 表达式
    val result = try {
        sum_1("s".toInt(), 3)
    } catch (e: Exception) {
        0
    }finally {
        print("result finally\n")
    }
    println("result = $result")
}

fun sum_1(arg1: Int, arg2: Int): Int {
    return arg1 + arg2
}