pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    buildscript {
        dependencies {
            classpath("com.dropbox.affectedmoduledetector:affectedmoduledetector:0.1.5")
        }
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

rootProject.name = "Hyrule Compendium"

includeBuild("plugins")

include(":app")

include(":features")
include(":features:categories")
include(":features:category-entries")

include(":design")

include(":platform")
include(":platform:network")
include(":platform:testing")
include(":platform:imageloader")
