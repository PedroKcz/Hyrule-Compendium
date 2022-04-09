plugins {
    id("hyrule.android.library")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(":design"))
    implementation(project(":platform:imageloader"))

    androidTestImplementation(Koin.junit4)
}
