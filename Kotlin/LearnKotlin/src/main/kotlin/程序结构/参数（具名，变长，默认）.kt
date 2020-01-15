package 程序结构

//具名参数：给函数形参附上实参
fun sum_2(arg1: Int, arg2: Int) = arg1 + arg2 //使用：sum_2(arg1 = 2, arg2 = 3)

//变长参数，使用：hello(1,2,3,4,str = "hello")
fun hello(vararg arg1: Int, str: String) {
    arg1.forEach(::println)
    println(str)
}

fun main(vararg args: String) {
    hello(1, 2, 3, 4, str = "hello")
    val array = intArrayOf(1, 2, 3, 4);
    //* 可以展开 array
    hello(*array, str = "hello")
    hi(ints = *array)
}

//默认参数，在方法定义的时候就默认一个参数
fun hi(str: String = "你好", vararg ints: Int) {
    ints.forEach(::println)
    println(str)
}
