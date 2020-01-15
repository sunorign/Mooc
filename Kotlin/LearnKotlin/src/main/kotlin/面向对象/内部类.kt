package 面向对象

open class Outter {
    val a: Int = 0

    //静态内部类
    class Inner {

    }

    //非静态内部类（如果内部类依赖外部类的实例）
    inner class Inner_1 {
        val a: Int = 5
        fun hello() {
            println(this@Outter.a)
        }
    }
}

interface OnClickListener {
    fun onClick()
}

class View {
    var onClickListener: OnClickListener? = null
}

fun main(args: Array<String>) {
    val inner = Outter().Inner_1()
    val view = View()
    //匿名内部类，可以继承另一个类再实现一个接口（java 做不到）
    view.onClickListener = object : Outter(), OnClickListener {
        override fun onClick() {

        }
    }
}