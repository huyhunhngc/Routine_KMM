plugins {
    id("dev.icerock.mobile.multiplatform-resources") version "0.22.3"
}
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.androidBuildTools)
        classpath(Deps.sqlDelightGradlePlugin)
        classpath(Deps.hiltGradlePlugin)
        classpath("dev.icerock.moko:resources-generator:0.22.3")
    }
}
apply(plugin = "dev.icerock.mobile.multiplatform-resources")

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
