package com.hyrule

import com.hyrule.config.detekt.implementDetekt
import com.hyrule.config.hyruleversion.configHyruleVersion
import com.hyrule.config.jetpack.implementDefaultJetpack
import com.hyrule.config.kotlin.configureAndroidJVMVersion
import com.hyrule.config.kotlin.implementKotlinLibs
import com.hyrule.config.ktlint.implementKtLint
import com.hyrule.config.plugins.AndroidGradle
import com.hyrule.config.plugins.Kotlin
import com.hyrule.config.sdk.configureSdkVersion
import com.hyrule.config.testing.implementIntegrationTesting
import com.hyrule.config.testing.implementUnitTesting
import org.gradle.api.Plugin
import org.gradle.api.Project

class HyruleAndroidApplication : Plugin<Project> {

    override fun apply(target: Project) {
        target.addAndroidApplicationPlugins()
        target.configureSdkVersion()
        target.configHyruleVersion()
        target.configureAndroidJVMVersion()
        target.implementKotlinLibs()
        target.implementDefaultJetpack()
        target.implementKtLint()
        target.implementDetekt()
        target.implementUnitTesting()
        target.implementIntegrationTesting()
    }

    private fun Project.addAndroidApplicationPlugins() {
        plugins.apply {
            apply(AndroidGradle.applicationPlugin)
            apply(Kotlin.Plugins.android)
            apply(Kotlin.Plugins.parcelize)
            apply(Kotlin.Plugins.kapt)
            apply(Kotlin.Plugins.serialization)
        }
    }
}
