package com.hyrule.config.testing

import com.hyrule.config.utils.androidExtension
import com.hyrule.config.utils.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.implementUnitTesting() {
    dependencies {
        implementation("junit:junit:_")
        implementation("io.mockk:mockk:_")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:_")
    }
}

fun Project.implementIntegrationTesting() {
    androidExtension {
        buildTypes.getByName("debug") { isTestCoverageEnabled = true }

        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    dependencies {
        // TODO
    }
}
