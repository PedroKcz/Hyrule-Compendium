package com.hyrule.config.sdk

import com.hyrule.config.utils.androidExtension
import org.gradle.api.Project

object HyruleSdkVersion {
    const val min: Int = 23
    const val target: Int = 32
}

fun Project.configureSdkVersion() {
    androidExtension {

        compileSdkVersion(HyruleSdkVersion.target)
        defaultConfig {
            targetSdk = HyruleSdkVersion.target
            minSdk = HyruleSdkVersion.min
        }
    }
}
