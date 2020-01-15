//基础参数
val base = mapOf(
        "kotlinVersion" to "1.3.11",    //kotlin 版本
        "supportVersion" to "28.0.0",   //support 版本
        "gradleToolVersion" to "3.3.0"  //gradleTool 版本
)
//Android sdk版本
val android = mapOf(
        "minSdkVersion" to 23,
        "targetSdkVersion" to 28,
        "compileSdkVersion" to 28
)
//依赖版本管理
val dependencies = mapOf(
        "constraint-layout" to "1.1.3",
        "runner" to "1.0.2",
        "junit" to "4.12"
)

base.entries.forEach {
    project.extra.set(it.key, it.value)
}
android.entries.forEach {
    project.extra.set(it.key, it.value)
}
dependencies.entries.forEach {
    project.extra.set(it.key, it.value)
}