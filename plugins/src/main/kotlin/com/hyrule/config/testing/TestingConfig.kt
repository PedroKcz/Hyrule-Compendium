package com.hyrule.config.testing

import com.hyrule.config.utils.androidExtension
import com.hyrule.config.utils.androidTestImplementation
import com.hyrule.config.utils.debugImplementation
import com.hyrule.config.utils.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.implementUnitTesting() {
    dependencies {
        testImplementation("junit:junit:_")
        testImplementation("io.mockk:mockk:_")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:_")
        testImplementation(project(":platform:testing"))
    }
}

fun Project.implementIntegrationTesting() {
    androidExtension {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    dependencies {
        androidTestImplementation(project(":platform:testing"))

        androidTestImplementation("androidx.compose.ui:ui-test-junit4:_")
        debugImplementation("androidx.compose.ui:ui-test-manifest:_")

        androidTestImplementation("androidx.test.espresso:espresso-core:_")

        androidTestImplementation("androidx.test:runner:_")
        androidTestImplementation("androidx.test:rules:_")

        androidTestImplementation("androidx.navigation:navigation-testing:_")

        androidTestImplementation("io.mockk:mockk-android:_")
    }
}
