package 程序结构

class Complex(var real: Double, var imaginary: Double) {
    //运算符方法 operator
    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }
    //更改参数
    operator fun plus(other: Int): Complex {
        return Complex(real + other,imaginary + other)
    }
    //更改返回值类型
    operator fun plus(other: Any): Int {
        return real.toInt()
    }
    override fun toString(): String {
        return "$real + $imaginary"
    }
}

fun main(args: Array<String>) {
    val c1 = Complex(3.0, 4.0)
    val c2 = Complex(5.0, 6.0)
    println(c1 + c2)    //8.0 + 10.0
    println(c1 + 6)     //9.0 + 10.0
    println(c1 + "hello")   //3
}
