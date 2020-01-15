package 数据类型

val range: IntRange = 0..1024 //[0,1024]
val range_exclusive = 0 until 10 //[0,10) = [0,9]
val empty_range: IntRange = 0..-1

fun main(args: Array<String>) {
    println(empty_range.isEmpty())
    println(range.contains(500))
    println(50 in range)
    /**
     * 循环输出
     */
    for (i in range_exclusive) {
        print("$i ")
    }
}
