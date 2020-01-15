import com.android.build.gradle.internal.api.ApkVariantOutputImpl

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
android {
    val minSdk = rootProject.extra["minSdkVersion"] as Int
    val targetSdk = rootProject.extra["targetSdkVersion"] as Int
    val compileSdk = rootProject.extra["compileSdkVersion"] as Int

    compileSdkVersion(compileSdk)
    defaultConfig {
        applicationId = "com.zexinchen.shimmer"
        minSdkVersion(minSdk)
        targetSdkVersion(targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("release") {
            storeFile = file("../debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        getByName("debug") {
            storeFile = file("../debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    packagingOptions {
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
    }
    android.applicationVariants.all {
        outputs.all {
            if (this is ApkVariantOutputImpl) {
                this.outputFileName = "微光_${versionCode}_$versionName.apk"
            }
        }
    }
}
val ktv = rootProject.extra["kotlinVersion"] as String
val supportVersion = rootProject.extra["supportVersion"] as String
val constraintLayoutVersion = rootProject.extra["constraint-layout"] as String
val junitVersion = rootProject.extra["junit"] as String
val runnerVersion = rootProject.extra["runner"] as String

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("com.android.support:appcompat-v7:$supportVersion")
    implementation("com.android.support:design:$supportVersion")
    implementation("com.android.support.constraint:constraint-layout:$constraintLayoutVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$ktv")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("com.android.support.test:runner:$runnerVersion")
}
