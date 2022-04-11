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
        classpath("com.dropbox.affectedmoduledetector:affectedmoduledetector:_")
    }
}

apply { plugin("com.dropbox.affectedmoduledetector") }

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
