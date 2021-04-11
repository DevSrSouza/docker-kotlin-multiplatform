package br.com.devsrsouza.kotlin.docker.utils.streaming

import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import br.com.devsrsouza.kotlin.docker.infrastructure.RequestConfig
import br.com.devsrsouza.kotlin.docker.infrastructure.RequestMethod
import kotlin.reflect.KClass

/**
 * Emits when responds with new line
 *
 * This custom API using `expect` is required because of a bug found in the Ktor Client
 * https://youtrack.jetbrains.com/issue/KTOR-2494
 */
expect fun requestStreaming(
    method: RequestMethod,
    url: Url,
    headers: Map<String, String> = mapOf(),
    contentType: ContentType = ContentType.Application.Json,
    body: String? = null,
): Flow<String>

@OptIn(InternalSerializationApi::class)
fun <T : Any> requestStreaming(
    json: Json,
    url: Url,
    requestConfig: RequestConfig,
    contentType: ContentType = ContentType.Application.Json,
    body: Any? = null,
    responseType: KClass<T>,
): Flow<T> {
    return requestStreaming(
        method = requestConfig.method,
        url = url,
        headers = requestConfig.headers,
        contentType = contentType,
        body = body?.let { serializeAnyWithKtxSerializer(json, it) }
    ).map { event ->
        json.decodeFromString(responseType.serializer(), event)
    }
}

@OptIn(InternalSerializationApi::class)
private fun serializeAnyWithKtxSerializer(json: Json, any: Any):String {
    return json.encodeToString(any::class.serializer() as KSerializer<Any>, any)
}