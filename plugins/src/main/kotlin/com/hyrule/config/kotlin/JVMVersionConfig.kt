package com.hyrule.config.kotlin

import com.hyrule.config.utils.androidExtension
import com.hyrule.config.utils.javaExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureAndroidJVMVersion() {
    androidExtension {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    configureKotlinJvm()
}

fun Project.configureJVMVersion() {
    javaExtension {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configureKotlinJvm()
}

private fun Project.configureKotlinJvm() {
    project.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
