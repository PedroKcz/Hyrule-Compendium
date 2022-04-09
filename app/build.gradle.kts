plugins {
    id("hyrule.android.application")
}

dependencies {
    implementation(Koin.android)
    implementation(Koin.compose)
    implementation(Ktor.client.cio)

    implementation(project(":features:categories"))
    implementation(project(":features:category-entries"))
    implementation(project(":design"))
    implementation(project(":platform:network"))

    testImplementation(Koin.test)
}
