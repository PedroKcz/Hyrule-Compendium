repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    java
    `kotlin-dsl`
    `java-gradle-plugin`
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
}


gradlePlugin {
    plugins {
        register("hyrule.android.application") {
            id = "hyrule.android.application"
            implementationClass = "com.hyrule.HyruleAndroidApplication"
        }

        register("hyrule.android.library") {
            id = "hyrule.android.library"
            implementationClass = "com.hyrule.HyruleAndroidLibrary"
        }

        register("hyrule.kotlin.library") {
            id = "hyrule.kotlin.library"
            implementationClass = "com.hyrule.HyruleKotlinLibrary"
        }
    }
}
