package 程序结构

/**
 * fun 函数名（参数：参数类型）：返回值｛...｝
 **/
fun main(args: Array<String>): Unit {
    /**
     * 函数体
     */
    println(sum(1, 2))
    println(int2Long(3))
}

/**
 * 如果函数只返回表达式，可以简化如下
 */
fun sum(arg1: Int, arg2: Int) = arg1 + arg2

/**
 * 用变量承载函数
 **/
val int2Long = fun(x: Int): Long {
    return x.toLong()
}