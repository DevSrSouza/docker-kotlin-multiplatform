package br.com.devsrsouza.kotlin.docker.utils.streaming

import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import br.com.devsrsouza.kotlin.docker.infrastructure.RequestMethod

actual fun requestStreaming(
    method: RequestMethod,
    url: Url,
    headers: Map<String, String>,
    contentType: ContentType,
    body: String?,
): Flow<String> {
    val mediaType = "${contentType.contentType}/${contentType.contentSubtype}".toMediaType()

    val client = OkHttpClientStreamingProvider.get()

    val call = client.newCall(
        Request.Builder()
            .url(url.toString())
            .apply { headers.forEach { addHeader(it.key, it.value) } }
            .apply { method(method.name, body?.toRequestBody(mediaType))  }
            .build()
    )

    return flow {
        val streamingSource = call.execute().body!!.source()

        while (true) {
            emit(streamingSource.readUtf8LineStrict())
        }
    }
}