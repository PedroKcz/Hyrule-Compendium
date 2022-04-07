package com.hyrule.config.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(lib: String) {
    add("implementation", lib)
}

fun DependencyHandlerScope.androidTestImplementation(lib: String) {
    add("androidTestImplementation", lib)
}

fun DependencyHandlerScope.debugImplementation(lib: String) {
    add("debugImplementation", lib)
}
