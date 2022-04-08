plugins {
    id("hyrule.android.library")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(":design"))

    androidTestImplementation(Koin.junit4)
}
