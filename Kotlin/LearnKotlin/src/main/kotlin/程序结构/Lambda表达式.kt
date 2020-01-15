package 程序结构

/**
 * Lambda 表达式定义
 * 1.匿名函数
 * 2.写法：{[参数列表] -> [函数体,最后一行是返回值]}
 */
// (Int,Int) -> Int
val sum = { arg1: Int, arg2: Int -> arg1 + arg2 }

/**
 * 默认返回最后一行
 */
val printHello = {
    ->
    println("Hello")
    true
}

fun main(args: Array<String>) {
    println(sum(1, 2)) // 等价于 sum.invoke(1,2)
    var isPrint: Boolean = printHello()
    println(isPrint)    // true

    // 数组遍历
    args.forEach {
        print(it)
    }
    args.forEach({ element -> print(element) })
    args.forEach(::print)
    //中断遍历用 标签名@ return@标签名 跳出 Lambda 表达式
    args.forEach ForEach@{
        if(it == "@") return@ForEach
        print(it)
    }
    println("hello")
}