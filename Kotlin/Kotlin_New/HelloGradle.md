- Terminal 里面执行  `gradlew --debug` 可以输出debug 日志，用来排查构建时的问题
- gradlew 是linux/mac 下的执行文件，gradlew.bat 是windows 下的启动脚本
- gradle wrapper 的地址在 gradle-wrapper.properties 里查看，包括本地存储的地址和下载的地址

```groovy
//自定义任务，在 gradle 工具栏里可以找到 hello group
task helloWorld(group:"hello"){
    println("hello")
    doLast{
        //真正执行的时候才会操作
        println("World")
    }
}
```

