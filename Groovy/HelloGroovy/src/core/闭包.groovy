package core
/**
 * 创建闭包
 */
def closer = { println 'Hello groovy!'}
/**
 * 调用闭包
 */
closer.call()
//closer()

/**
 * 闭包传递参数
 */
closer = { String name,int age -> println "Hello $name, My age is ${age}"}
closer.call("小明",1)
//closer("小明",1)
closer = {
    println("${it} param")  //如果不指定参数，闭包就会有个 it 默认参数
}
closer("default")

/**
 * 闭包返回值
 */
closer = {
    "Hello closer"  //最后一行就是返回值
}
println closer()
