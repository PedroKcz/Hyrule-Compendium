package com.hyrule.config.testing

import com.hyrule.config.utils.androidExtension
import com.hyrule.config.utils.androidTestImplementation
import com.hyrule.config.utils.debugImplementation
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
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    dependencies {
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:_")
        debugImplementation("androidx.compose.ui:ui-test-manifest:_")

        androidTestImplementation("androidx.test.espresso:espresso-core:_")

        androidTestImplementation("androidx.test:runner:_")
        androidTestImplementation("androidx.test:rules:_")

        androidTestImplementation("androidx.navigation:navigation-testing:_")
    }
}
