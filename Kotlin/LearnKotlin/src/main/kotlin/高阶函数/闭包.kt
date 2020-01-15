package 高阶函数

fun makeFun(): () -> Unit {
    //这个状态是被持有的，不会释放
    var count = 0
    return fun() {
        println(++count)
    }
}

fun main(args: Array<String>) {
    val x = makeFun()
    x()
    x()
    x()
}
/*
闭包：
1、 函数运行的环境
2、 持有函数运行的状态
3、 函数内部可以定义函数
4、 函数内部也可以定义类
 */