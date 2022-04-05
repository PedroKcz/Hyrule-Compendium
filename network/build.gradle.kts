plugins {
    id("kotlin")
    id("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Ktor.client.core)
    implementation(Ktor.client.cio)
    implementation(Ktor.client.serialization)
    implementation(Ktor.client.logging)

    testImplementation(Ktor.client.mock)
    testImplementation(Testing.junit4)
}
