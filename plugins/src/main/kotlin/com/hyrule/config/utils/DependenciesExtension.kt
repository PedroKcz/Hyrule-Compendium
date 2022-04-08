package com.hyrule.config.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(lib: String) {
    add("implementation", lib)
}

fun DependencyHandlerScope.testImplementation(lib: String) {
    add("testImplementation", lib)
}

fun DependencyHandlerScope.testImplementation(project: Project) {
    add("testImplementation", project)
}

fun DependencyHandlerScope.androidTestImplementation(lib: String) {
    add("androidTestImplementation", lib)
}

fun DependencyHandlerScope.androidTestImplementation(project: Project) {
    add("androidTestImplementation", project)
}

fun DependencyHandlerScope.debugImplementation(lib: String) {
    add("debugImplementation", lib)
}
