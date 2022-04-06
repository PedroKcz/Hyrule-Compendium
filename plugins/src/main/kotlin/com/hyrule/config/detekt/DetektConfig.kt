package com.hyrule.config.detekt

import com.hyrule.config.utils.detektExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.api.Project

private const val CONFIG_PATH =
    "/plugins/src/main/kotlin/com/hyrule/config/detekt/detekt-config.yml"

fun Project.implementDetekt() {
    plugins.apply {
        apply("io.gitlab.arturbosch.detekt")
    }

    detektExtension {
        parallel = false
        config = files(rootDir.path + CONFIG_PATH)

        ignoreFailures = false
        ignoredBuildTypes = listOf("release")
    }

    tasks.withType(Detekt::class.java).configureEach {
        jvmTarget = "1.8"
    }
    tasks.withType(DetektCreateBaselineTask::class.java).configureEach {
        jvmTarget = "1.8"
    }
}
