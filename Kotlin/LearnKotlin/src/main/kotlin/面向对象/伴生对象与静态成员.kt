package 面向对象

fun main(args: Array<String>) {
    //1.静态成员最好使用包级函数、变量替代,eg:
    val a = minOf(1, 2)

    //2.静态方法（伴生对象）
    val latitude = Latitude.ofDouble(3.0)
    //2.1 java使用 Latitude latitude = Latitude.Companion.ofDouble(3.0)

}

class Latitude private constructor(val value: Double) {
    //伴生对象，相当 java 里面的静态方法
    companion object {
        //加了 JvmStatic 就可以在Java中直接使用
        @JvmStatic
        fun ofDouble(double: Double): Latitude {
            return Latitude(double)
        }
    }

    fun ofLatitude(latitude: Latitude): Latitude {
        return Latitude(latitude.value)
    }
    //加了 JvmField 就可以在Java中直接使用
    @JvmField
    val TAG: String = "Latitude"
}