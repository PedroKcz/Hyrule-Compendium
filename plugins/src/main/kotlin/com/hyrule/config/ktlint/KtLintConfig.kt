package com.hyrule.config.ktlint

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

fun Project.implementKtLint() {
    plugins.apply {
        apply("org.jlleitschuh.gradle.ktlint")
    }

    configure<KtlintExtension> {
        android.set(true)
        additionalEditorconfigFile.set(file("/.editorconfig"))
    }
}
