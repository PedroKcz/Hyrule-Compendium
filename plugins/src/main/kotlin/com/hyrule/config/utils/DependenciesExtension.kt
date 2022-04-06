package com.hyrule.config.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(lib: String) {
    add("implementation", lib)
}
