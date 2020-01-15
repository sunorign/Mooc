package 高阶函数

data class ListNode(val value: Int, var next: ListNode? = null)

tailrec fun findListNode(head: ListNode?, value: Int): ListNode? {
    head ?: return null
    if (head.value == value) return head
    return findListNode(head.next, value)
}

fun main(args:Array<String>){
    val MAX_NODE_COUNT = 10
    val head = ListNode(0)
    var p = head
    for (i in 1..MAX_NODE_COUNT){
        p.next = ListNode(i)
        p = p.next!!
    }
    println(findListNode(head,MAX_NODE_COUNT - 2)?.value)
}

/*
调用完自己后没有操作的递归

tailrec 关键字提示编译器尾递归优化

尾递归和迭代的关系
 */