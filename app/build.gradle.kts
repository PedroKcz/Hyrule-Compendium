plugins {
    id("hyrule.android.application")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(":features:categories"))

    testImplementation(Koin.test)
    testImplementation(Testing.junit4)
}
