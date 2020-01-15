package 高阶函数

fun main(args: Array<String>) {
    args.forEach(::println)

    val helloWorld = Hello::world

    args.filter(String::isNotEmpty)

    val pdfPrinter = PdfPrinter()
    args.forEach(pdfPrinter::println)
}

class PdfPrinter {
    fun println(any: Any) {
        kotlin.io.println(any)
    }
}

class Hello {
    fun world() {
        println("Hello world")
    }
}
/*
基本概念：

1.传入或者返回函数的函数
2.函数引用 ::println
3.带有 Receiver 的引用 pdfPrinter::println
 */