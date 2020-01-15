package 数据类型

var arrayOfInt:IntArray = intArrayOf(1,2,3,4,5)
var arrayOfChar:CharArray = charArrayOf('a','b','c','d')
var arrayOfPerson:Array<Person> = arrayOf(Person("小明","男"),Person("小红","女"))

fun main(args:Array<String>){
    /**
     * 循环输出
     */
    for (man in arrayOfPerson){
        println(man.name)
    }

    println(arrayOfInt[0]) // 1
    arrayOfInt[0] = 3

    /**
     *将 char 数组拼接成 String
     */
    println(arrayOfChar.joinToString(""))   //abcd
    println(arrayOfInt.slice(1..2)) //  [2, 3]
}
