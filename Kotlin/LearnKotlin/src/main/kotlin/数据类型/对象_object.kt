package 数据类型

/**
 * import com.package.car as bus 导入类，并更改类名(将car 更改成 bus)
 */
fun main(args: Array<String>) {
    /**
     * 对象初始化
     */
    var male: Male = Male("小明", "男")
    var feMale: FeMale = FeMale("小红", "女")

    /**
     *  判断类的归属，类似 java 的 instanceof
     */
    println(male is Person) //true

    /**
     *  ? 表示可 null 类型
     *  !! 表示明确了值不为 null
     */
    var value: String? = "HelloWorld"
    println(value!!.length)
    var name: String = getName() ?: return //判断 getName() 是否为空，为空的话就 return，否则赋值
    println(getName()?.length) //getName() 如果为空则返回 null，否则返回 length

    /**
     * 安全类型转换
     * 如果转换失败返回 null，不抛异常
     */
    var theMale: Male? = Person() as? Male
}

/**
 * Any 类是 kotlin 中所有类的父类
 * 类和对象
 */
//open 后类才能被继承
open class Person(var name: String?, var sex: String?) {
    constructor() : this(null, null) {
    }

    init {
        println("new 了一个${this.javaClass.simpleName},姓名是${name},性别是${sex} ")
    }
}

class Male(name: String, sex: String) : Person(name, sex)
class FeMale(name: String, sex: String) : Person(name, sex)

/**
 * 方法的定义
 * 返回 null 类型需要加 ?
 */
fun getName(): String? {
    return null
}