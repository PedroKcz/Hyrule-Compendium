plugins {
    id("hyrule.android.application")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(":features:categories"))
    implementation(project(":design"))

    testImplementation(Koin.test)
    testImplementation(Testing.junit4)
}
