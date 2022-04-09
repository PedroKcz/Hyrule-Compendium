plugins {
    id("hyrule.android.library")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(Ktor.client.core)
    implementation(Ktor.client.serialization)

    implementation(project(":design"))
    implementation(project(":platform:network"))
    implementation(project(":platform:imageloader"))

    androidTestImplementation(Koin.junit4)
}
