package 面向对象

/*
密封类是包含了一组受限的类集合，因为里面的类都是继承自这个密封类的。
但是其和其他继承类（open）的区别在，密封类可以不被此文件外被继承，有效保护代码。
但是，其密封类的子类的扩展是是可以在程序中任何位置的，即可以不在同一文件下。

注意：密封类是不能被实例化的

使用密封类的好处：
1.有效的保护代码
2.在使用when表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个else子句了。（鸡肋）
 */
sealed class Operation {
    class Add(val value: Int) : Operation()
    class Substract(val value: Int) : Operation()
    object Multiply : Operation()   //单例模式
}

// 其子类可以定在密封类外部，但是必须在同一文件中。`v1.1`之前只能定义在密封类内部
object Divide : Operation()

fun main(args: Array<String>) {
    /**
     *密封类与枚举的不同？枚举类的中的每一个枚举常量都只能存在一个实例。而密封类的子类可以存在多个实例。
     */
    val add = Operation.Add(2)
    val add2 = Operation.Add(2)
}