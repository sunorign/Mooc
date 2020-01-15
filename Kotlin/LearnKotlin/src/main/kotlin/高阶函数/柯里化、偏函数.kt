package 高阶函数

import java.io.OutputStream
import java.nio.charset.Charset

/*
柯里化：
简单来说就是多元函数变换成一元函数调用链
 */

fun log(tag: String, target: OutputStream, message: Any?) {
    target.write("[$tag] $message\n".toByteArray())
}

fun pt(a: Int, b: Int, c: Int) {
    println(a + b + c)
}

fun main(args: Array<String>) {
    ::log.curried()("benny")(System.out)("HelloWorld Again")
    ::pt.curried()(1)(2)(3)

    /*
      偏函数
     */
    val consoleLogWithTag = (::log.curried())("benny")(System.out)
    consoleLogWithTag("HelloAgain Again.")
    val bytes = "我是中国人".toByteArray(charset("GBK"))
    val makeStringFromGbkBytes = makeStringFromGbkBytes(bytes)
}

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried() = fun(p1: P1) = fun(p2: P2) = fun(p3: P3) = this(p1, p2, p3)

val makeString = fun(byteArray: ByteArray, charset: Charset): String {
    return String(byteArray, charset)
}

val makeStringFromGbkBytes = makeString.partial2(charset("GBK"))

fun <P1, P2, R> Function2<P1, P2, R>.partial2(p2: P2) = fun(p1: P1) = this(p1, p2)