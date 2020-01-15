package 领域特定语言

fun main(args: Array<String>) {
    html {
        "id"("htmlId")
        "head"{
            "id"("headId")
        }
        body {
            id = "bodyId"
            `class` = "bodyClass"
            "a"{
                "href"("https://www.baidu.com")
                +"百度"
            }
        }
    }.render().let(::print)
}