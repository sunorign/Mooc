package 程序结构

fun main(args:Array<String>){
    //value 输出
    for (arg in args){
        println(arg)
    }
    //index 和 value 输出
    for ((index,value) in args.withIndex()){
        println("$index -> $value")
    }
}