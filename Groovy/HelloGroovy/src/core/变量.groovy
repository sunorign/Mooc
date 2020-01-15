package core
/**
 * 变量
 */
//强类型定义变量
int num = 10
println num.class //==> class java.lang.Integer

//弱类型定义变量
def x = 0
println x.class
def y = 1.0
println y.class
def name = "Hello"
println name.class

/**
 * 字符串
 */

//单引号不可扩展
def str = 'a single string'
println str.class

//三引号
def longStr = '''\
line one
line two
line three'''
println longStr

//双引号可扩展 ${var}
def dStr = "1 + 2 = ${1 + 2}"
println dStr
println dStr.class
