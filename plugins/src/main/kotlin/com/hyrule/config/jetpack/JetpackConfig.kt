@file:Suppress("UnstableApiUsage")

package com.hyrule.config.jetpack

import com.hyrule.config.utils.androidExtension
import com.hyrule.config.utils.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.implementDefaultJetpack() {
    implementCompose()
    implementNavigation()
    implementLifecycle()
}

private fun Project.implementCompose() {
    androidExtension {
        buildFeatures.compose = true
        defaultConfig.vectorDrawables {
            useSupportLibrary = true
        }
    }

    dependencies {
        implementation("androidx.compose.ui:ui:_")
        implementation("androidx.compose.ui:ui-tooling:_")
        implementation("androidx.compose.foundation:foundation:_")
        implementation("androidx.compose.material:material:_")
        implementation("androidx.compose.runtime:runtime:_")
        implementation("androidx.compose.compiler:compiler:_")
    }
}

private fun Project.implementNavigation() {
    plugins.apply("androidx.navigation.safeargs.kotlin")

    dependencies {
        implementation("androidx.navigation:navigation-fragment-ktx:_")
        implementation("androidx.navigation:navigation-ui-ktx:_")
        implementation("androidx.navigation:navigation-compose:_")
    }
}

private fun Project.implementLifecycle() {
    dependencies {
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:_")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:_")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:_")
        implementation("androidx.lifecycle:lifecycle-common-java8:_")
    }
}
