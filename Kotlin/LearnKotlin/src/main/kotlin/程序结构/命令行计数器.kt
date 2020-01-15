package 程序结构

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    while (true) {
        try {
            println("请输入算式子，例如 3 + 4")
            val input = readLine() ?: break
            val splits = input.trim().split(" ")
            if (splits.size < 3) {
                throw IllegalArgumentException("参数个数不对")
            }
            val arg1 = splits[0].toDouble()
            val op = splits[1]
            val arg2 = splits[2].toDouble()
            println("$arg1 $op $arg2 = ${Operator(op)(arg1, arg2)}")
        } catch (e: NumberFormatException) {
            println("您确定输入的都是数字吗？")
        } catch (e: IllegalArgumentException) {
            println("您确定是用空格分隔的吗？")
        } catch (e: Exception) {
            println("程序遇到未知异常：${e.message}")
        }
        println("再来一发？[Y]")
        val cmd = readLine()
        if (cmd == null || cmd.toLowerCase() != "y") {
            break
        }
    }
}

class Operator(op: String) {
    val opFun: (left: Double, right: Double) -> Double

    init {
        opFun = when (op) {
            "+" -> { l, r -> l + r }
            "-" -> { l, r -> l - r }
            "*" -> { l, r -> l * r }
            "/" -> { l, r -> l / r }
            "%" -> { l, r -> l % r }
            else -> {
                throw UnsupportedOperationException(op)
            }
        }
    }

    operator fun invoke(left: Double, right: Double): Double {
        return opFun(left, right)
    }
}