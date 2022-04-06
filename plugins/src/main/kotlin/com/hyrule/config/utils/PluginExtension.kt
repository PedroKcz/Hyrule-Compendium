package com.hyrule.config.utils

import com.android.build.gradle.BaseExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

fun Project.getAndroidExtension(): BaseExtension? {
    with(extensions.getByName("android")) {
        return if (this is BaseExtension) this else null
    }
}

fun Project.androidExtension(lambda: BaseExtension.() -> Unit) {
    with(extensions.getByName("android")) {
        if (this is BaseExtension) this.lambda() else null
    }
}

fun Project.javaExtension(lambda: JavaPluginExtension.() -> Unit) {
    with(extensions.getByName("java")) {
        if (this is JavaPluginExtension) this.lambda() else null
    }
}

fun Project.detektExtension(lambda: DetektExtension.() -> Unit) {
    with(extensions.getByName("detekt")) {
        if (this is DetektExtension) this.lambda() else null
    }
}
