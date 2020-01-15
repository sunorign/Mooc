package 领域特定语言

fun html(block: Tag.() -> Unit): Tag {
    return Tag("html").apply(block)
}

fun Tag.head(block: Head.() -> Unit){
    this@head + Head().apply(block)
}
fun Tag.body(block: Body.() -> Unit) {
    this@body + Body().apply(block)
}

class StringNode(val content: String) : Node {
    override fun render() = content
}
class Head:Tag("head")

class Body:Tag("body"){
    var id by MapDelegate(proerties)

    var `class` by MapDelegate(proerties)
}