package core

def x = 1.23
def result
/**
 * 强大的 switch case
 */
switch (x) {
    case 'foo':
        result = 'found foo'
        break
    case 'bar':
        result = 'bar'
        break
    case [4, 5, 6, 'inlist']:
        result = 'list'     //列表
        break
    case 12..30:
        result = 'range'    //范围
        break
    case Integer:
        result = 'integer'
        break
    case BigDecimal:
        result = 'big decimal'
        break
    default: result = 'default'
}
println result

/**
 * for 循环变体
 */
//对范围的循环
def sum = 0
for (i in 0..9){
    sum += i
}
println(sum)

//对 list 的循环
sum = 0
for (i in [1,2,3,4,5,6,7,8,9,10]){
    sum += i
}
println(sum)

//对 Map 进行循环
sum = 0
for (i in ['one':1,'two':2,'three':3]){
    sum += i.value
}
println(sum)
