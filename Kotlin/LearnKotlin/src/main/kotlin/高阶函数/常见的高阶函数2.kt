package 高阶函数

import java.io.BufferedReader
import java.io.FileReader

data class Person(val name: String, val age: Int) {
    fun work() {
        println("$name is working")
    }
}

fun findPerson(): Person? {
    return null
}

fun main(args: Array<String>) {
    findPerson()?.let { (name, age) ->
        println(name)
        println(age)
    }
    findPerson()?.apply {
        work()
        println(age)
    }
    val br = BufferedReader(FileReader("file.txt")).use {
        var line: String?
        while (true) {
            line = it.readLine() ?: break
            println(line)
        }
    }
    val br1 = BufferedReader(FileReader("file.txt")).readText()
    val br2 = BufferedReader(FileReader("file.txt"))

    println(br1)
    with(br2) {
        var line: String?
        while (true) {
            line = readLine() ?: break
            println(line)
        }
        close()
    }
}