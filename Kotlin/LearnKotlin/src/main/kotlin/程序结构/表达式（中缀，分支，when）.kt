package 程序结构

class Cat {
    /**
     * infix 中缀表达式，可以允许不用 .() 调用函数
     */
    infix fun eat(fish: Fish): String {
        return "小猫吃鱼"
    }
}

class Fish

//分支
private const val USERNAME = "kotlin"
private const val PASSWORD = "12345"

private const val ADMIN_USER = "admin"
private const val ADMIN_PWD = "admin"

private const val DEBUG = 1
private const val USER = 0

fun main(args: Array<String>) {
    println(Cat() eat Fish()) //中缀表达式,小猫吃鱼

    /**
     * 分支表达式，if 是有返回值的，最后一行
     */
    val mode =
            if (args.isNotEmpty() && args[0] == "1") {
                DEBUG
            } else {
                USER
            }

    println("请输入用户名")
    val userName = readLine()
    println("请输入密码")
    val passWord = readLine()

    if (mode == DEBUG && userName == ADMIN_USER && passWord == ADMIN_PWD) {
        println("管理员登陆成功")
    } else if (userName == USERNAME && passWord == PASSWORD) {
        println("登陆成功！")
    } else {
        println("登陆失败！")
    }

    /**
     * when 类似 java 中的 switch
     */
    val x = 5
    when (x) {
        is Int -> println("Hello $x")
        in 1..100 -> println("$x is in 1..100")
        !in 1..100 -> println("$x is not in 1..100")
        args[0].toInt() -> println("x == args[0]")
    }
    val otherMode = when {
        args.isNotEmpty() && args[0] == "1" -> DEBUG
        else -> USER
    }
}