plugins {
    id("hyrule.android.library")
}

dependencies {
    implementation("com.airbnb.android:lottie-compose:_")

    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(":design"))

    androidTestImplementation(Koin.junit4)
}
