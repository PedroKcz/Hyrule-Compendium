package com.hyrule

import com.hyrule.config.detekt.implementDetekt
import com.hyrule.config.kotlin.configureJVMVersion
import com.hyrule.config.kotlin.implementKotlinLibs
import com.hyrule.config.ktlint.implementKtLint
import com.hyrule.config.plugins.Kotlin
import com.hyrule.config.testing.implementUnitTesting
import org.gradle.api.Plugin
import org.gradle.api.Project

class HyruleKotlinLibrary : Plugin<Project> {

    override fun apply(target: Project) {
        target.addKotlinLibPlugins()
        target.configureJVMVersion()
        target.implementKotlinLibs()
        target.implementKtLint()
        target.implementDetekt()
        target.implementUnitTesting()
    }

    private fun Project.addKotlinLibPlugins() {
        plugins.apply {
            apply(Kotlin.Plugins.kotlin)
            apply(Kotlin.Plugins.serialization)
        }
    }
}
