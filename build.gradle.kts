plugins {
    id("com.dropbox.affectedmoduledetector")
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("org.jetbrains.kotlin:kotlin-serialization:_")
        classpath(AndroidX.navigation.safeArgsGradlePlugin)
    }
}

affectedModuleDetector {
    baseDir = "${project.rootDir}"
    pathsAffectingAllModules = setOf("plugins/")
    logFilename = "output.log"
    logFolder = "${rootProject.buildDir}/affectedModuleDetector"
    specifiedBranch = "develop"
    compareFrom = "SpecifiedBranchCommit"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
