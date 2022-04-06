package com.hyrule.config.testing

import com.hyrule.config.utils.androidExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.implementUnitTesting() {
    dependencies {
        // TODO
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
