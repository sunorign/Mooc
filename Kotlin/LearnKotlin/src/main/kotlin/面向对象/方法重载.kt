package 面向对象

//方法重载和函数名和参数列表有关，和返回值无关
class Overloads {
//    @JvmOverloads
//    fun a(): Int {
//        return 0
//    }

    //默认参数，方法重载;添加 @JvmOverloads 即可在 java 中使用
    @JvmOverloads
    fun a(int: Int = 0): Int {
        return int
    }
}

//多用默认参数，少用方法重载