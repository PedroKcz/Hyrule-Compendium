package com.hyrule.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Test

class HyruleKtorClientTest {

    @Serializable
    data class DummyId(
        @SerialName("id") val id: String
    )

    @Test
    fun `it should parse info properly`() = runBlocking {
        val mockEngine = createMockSuccessResponse()

        val apiClient = HyruleKtorClient(mockEngine)
        val dummyId: DummyId = apiClient().get()

        assertEquals("42", dummyId.id)
    }

    @Test
    fun `it should build request with base url`() = runBlocking {
        var builtUrl = ""
        val mockEngine = createMockSuccessResponse { builtUrl = it.url.toString() }

        HyruleKtorClient(mockEngine)().get<DummyId>("bla")

        assertEquals("https://botw-compendium.herokuapp.com/api/v2/bla", builtUrl)
    }

    private fun createMockSuccessResponse(
        onRequest: (HttpRequestData) -> Unit = {}
    ) = MockEngine { request ->
        onRequest(request)
        respond(
            content = ByteReadChannel("""{"id":"42"}"""),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}
