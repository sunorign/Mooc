package 高阶函数

//f(g(x)) m(x) = f(g(x))

val add5 = { i: Int -> i + 5 }
val multiplyBy2 = { i: Int -> i * 2 }

fun main(args: Array<String>) {
    println(multiplyBy2(add5(3)))

    val add5AndMultiplyBy2 = add5 andThen multiplyBy2
    val add5ComposeMultiplyBy2 = add5 compose multiplyBy2
    println(add5AndMultiplyBy2(3))  // m(x) = f(g(x))
    println(add5ComposeMultiplyBy2(3)) //m(x) = g(f(x))
}

/*
   扩展方法：先扩展 Fuction1，使用中缀表达式 infix
 */
infix fun <P1, P2, R> Function1<P1, P2>.andThen(function: Function1<P2, R>): Function1<P1, R> {
    return fun(p1: P1): R {
        return function.invoke(this.invoke(p1))
    }
}

infix fun <P1, P2, R> Function1<P2, R>.compose(function: Function1<P1, P2>): Function1<P1, R> {
    return fun(p1: P1): R {
        return this.invoke(function.invoke(p1))
    }
}