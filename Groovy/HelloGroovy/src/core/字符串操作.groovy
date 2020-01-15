package core
def str = "groovy"
def str2 = 'gro'
//字符串填充
println str.center(8,"A")
println str.padLeft(8,"A")
//字符串范围输出
println str[0]
println str[0..1]
//字符串排除
println str.minus(str2)
println str - str2
//字符串首字母大写
println str.capitalize()
