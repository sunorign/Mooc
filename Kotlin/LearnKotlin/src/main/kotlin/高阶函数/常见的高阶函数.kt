package 高阶函数

fun main(args: Array<String>) {
    //1 集合映射
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    val newlist = list.map {
        it * 2 + 3
    }

    println("1.1 转 double 类型-------------------------------->")
    val newlist2 = list.map { Int::toDouble }
    newlist.forEach(::println)

    println("1.2 给 Intrange 排序-------------------------------->")
    val list2 = listOf(
            1..10,
            9..15
    )
    val flatList = list2.flatMap {
        it.map {
            "No.$it"
        }
    }
    flatList.forEach(::println)
    println("1.3 reduce 对调用者进行迭代，eg：求和------------------->")
    println(list.reduce { acc, i -> acc + i })

    println("1.4 阶乘--------------------------------->")
    (0..6).map(::factorial).forEach(::println)

    println("1.5 拼接字符串--------------------------------->")
    println((0..6).map(::factorial).fold(StringBuilder()) { acc, i ->
        acc.append(i).append(",")
    })
    println((0..6).joinToString(","))

    println("1.6 过滤,eg：输出奇数--------------------->")
    println((0..11).filter { it % 2 == 1 })
    println("1.7 位置过滤--------------------->")
    println((0..11).filterIndexed { index, i -> index % 2 == 1 })
    println("1.8 不符合条件停止--------------------->")
    println((0..11).takeWhile { it / 2 != 3 })
}

fun factorial(n: Int): Int {
    if (n == 0) return 1
    return (1..n).reduce { acc, i -> acc * i }
}