package com.hyrule.config.hyruleversion

import com.hyrule.config.utils.getAndroidExtension
import org.gradle.api.Project

object HyruleVersion {
    const val major: Int = 0
    const val minor: Int = 0
    const val patch: Int = 1
    const val build: Int = 0
}

fun Project.configHyruleVersion() {
    val appVersionName =
        "${HyruleVersion.major}.${HyruleVersion.minor}.${HyruleVersion.patch}"

    val appVersionCode = HyruleVersion.major * 1000000 +
            HyruleVersion.minor * 10000 +
            HyruleVersion.patch * 100 +
            HyruleVersion.build

    project.getAndroidExtension()?.defaultConfig?.apply {
        versionCode = appVersionCode
        versionName = appVersionName

        buildConfigField("String", "APP_VERSION", "\"$appVersionName\"")
    }
}
