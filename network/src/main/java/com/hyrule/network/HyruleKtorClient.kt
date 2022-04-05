package com.hyrule.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

private const val HOST_NAME = "botw-compendium.herokuapp.com"
private const val BASE_PATH = "/api/v2"

class HyruleKtorClient(
    private val engine: HttpClientEngine
) {

    operator fun invoke() = HttpClient(engine) {

        install(Logging) {
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }

            serializer = KotlinxSerializer(json)
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST_NAME
                encodedPath = BASE_PATH + encodedPath
            }
        }
    }
}
