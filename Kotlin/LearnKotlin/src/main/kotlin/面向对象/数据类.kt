package 面向对象

data class Country(val id: Int, val name: String)

fun main(args: Array<String>) {
    val china: Country = Country(0, "中国")
    println(china)
    println(china.component1())
    println(china.component2())

    val (id, name) = china
    println(id)
    println(name)

    for ((index, value) in args.withIndex()) {
        println(index)
        println(value)
    }
}
//allOpen,noArg 插件
/*
在工程 gradle 中使用

    dependencies{
        classpath "org.jetbrains.kotlin:kotlin-noarg:$kotlin_version"
        classpath "org.jetbrains.kotlin:kotlin-allopen:$kotlin_version"
    }
    apply plugin:"kotlin-noarg"
    apply plugin:"kotlin-allopen"

    //创建annotation文件
    noArg{
        annotation("net.println.kotlin.annotations.PoKo")
    }
    allOpen{
        annotation("net.println.kotlin.annotations.PoKo")
    }

    最后给 data 加 @Poko 注解
 */

/*
- 再见，javaBean
- 默认实现的 copy、toString 等方法
- componentN 方法
- allOpen 和 noArg 插件：可以在编译期把 final 去掉
 */