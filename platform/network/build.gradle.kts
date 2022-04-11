plugins {
    id("hyrule.kotlin.library")
}

dependencies {
    implementation(Ktor.client.core)
    implementation(Ktor.client.cio)
    implementation(Ktor.client.serialization)
    implementation(Ktor.client.logging)

    testImplementation(Ktor.client.mock)
    testImplementation(Testing.junit4)
}

affectedTestConfiguration {
    jvmTestTask = "test"
}
