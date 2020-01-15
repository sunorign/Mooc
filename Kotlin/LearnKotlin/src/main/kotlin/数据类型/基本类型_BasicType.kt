package 数据类型

/**
 * 数据定义
 * var： var是一个可变变量
 * val: val是一个只读变量，相当于 java 中的 final 变量
 */
var aBoolean: Boolean = true
val maxInt: Int = Int.MAX_VALUE
fun main(args:Array<String>){
    /**
     *  不支持隐式转换
     */
    var aInt: Int = 5
    var aLong: Long = aInt.toLong()

    /**
     *  字符串比较
     *  ==  比较内容
     *  === 比较引用值
     */
    var str: String = "HelloWorld"
    var strfromChar: String = String(charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'))
    println(str == strfromChar)     //true
    println(str === strfromChar)    //false

    /**
     * kotlin 中的转义字符
     *  \t  制表符
     *  \b  光标后退一个字符
     *  \n  回车
     *  \'  单引号
     *  \"  双引号
     *  \\  反斜杠
     *  \$  美元符号，Kotlin 支持美元符号开头的字符串模板
     *------------------------------------------------
     *   $  字符串模板的使用
     */
    var arg1: Int = 1
    var arg2: Int = 2
    println("" + arg1 + "+" + arg2 + "=" + (arg1 + arg2))   //1+2=3
    println("$arg1 + $arg2 = ${arg1 + arg2}")   //1 + 2 = 3

    var rawStr: String = """
        三个引号 ——> 原始字符串
        """
    println(rawStr.length)  //32

    /**
     * 打印类名
     */
    println(Person::class.java.simpleName)
}