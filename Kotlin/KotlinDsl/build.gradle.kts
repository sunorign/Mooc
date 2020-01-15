// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
   project.apply{
       from("config.gradle.kts")
   }
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val ktv: String = project.extra["kotlinVersion"] as String
        val gradleTool: String = project.extra["gradleToolVersion"] as String
        classpath("com.android.tools.build:gradle:$gradleTool")
        classpath(kotlin("gradle-plugin", ktv))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
