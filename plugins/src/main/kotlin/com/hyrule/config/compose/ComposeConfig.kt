@file:Suppress("UnstableApiUsage")

package com.hyrule.config.compose

import com.hyrule.config.utils.androidExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.implementCompose() {
    androidExtension {
        buildFeatures.compose = true
        // composeOptions.kotlinCompilerExtensionVersion =
        defaultConfig.vectorDrawables {
            useSupportLibrary = true
        }
    }

    dependencies {
        // TODO
    }
}
