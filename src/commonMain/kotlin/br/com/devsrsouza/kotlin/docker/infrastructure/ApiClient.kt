package br.com.devsrsouza.kotlin.docker.infrastructure

import br.com.devsrsouza.kotlin.docker.utils.API_PATH
import br.com.devsrsouza.kotlin.docker.utils.DEFAULT_DOCKER_SOCKET_FILE
import br.com.devsrsouza.kotlin.docker.utils.UnixDomain
import br.com.devsrsouza.kotlin.docker.utils.streaming.requestStreaming
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent
import io.ktor.http.*
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.PartData
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

import br.com.devsrsouza.kotlin.docker.apis.*
import br.com.devsrsouza.kotlin.docker.models.*
import br.com.devsrsouza.kotlin.docker.auth.*

open class ApiClient(
    val baseUrl: String,
    httpClientEngine: HttpClientEngine?,
    val json: Json
) {

    private val serializer: JsonSerializer by lazy {
        KotlinxSerializer(json).ignoreOutgoingContent()
    }

    val client: HttpClient by lazy {
        val jsonConfig: JsonFeature.Config.() -> Unit = { this.serializer = this@ApiClient.serializer }
        val clientConfig: (HttpClientConfig<*>) -> Unit = {
            it.install(JsonFeature, jsonConfig)
        }
        httpClientEngine?.let { HttpClient(it, clientConfig) } ?: HttpClient(clientConfig)
    }
    private val authentications: kotlin.collections.Map<String, br.com.devsrsouza.kotlin.docker.auth.Authentication>? = null

    companion object {
        const val BASE_URL = DEFAULT_DOCKER_SOCKET_FILE

        protected val UNSAFE_HEADERS = listOf(HttpHeaders.ContentType)
    }

    /**
     * Set the username for the first HTTP basic authentication.
     *
     * @param username Username
     */
    fun setUsername(username: String) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.HttpBasicAuth } as br.com.devsrsouza.kotlin.docker.auth.HttpBasicAuth?
                ?: throw Exception("No HTTP basic authentication configured")
        auth.username = username
    }

    /**
     * Set the password for the first HTTP basic authentication.
     *
     * @param password Password
     */
    fun setPassword(password: String) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.HttpBasicAuth } as br.com.devsrsouza.kotlin.docker.auth.HttpBasicAuth?
                ?: throw Exception("No HTTP basic authentication configured")
        auth.password = password
    }

    /**
     * Set the API key value for the first API key authentication.
     *
     * @param apiKey API key
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    fun setApiKey(apiKey: String, paramName: String? = null) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.ApiKeyAuth && (paramName == null || paramName == it.paramName)} as br.com.devsrsouza.kotlin.docker.auth.ApiKeyAuth?
                ?: throw Exception("No API key authentication configured")
        auth.apiKey = apiKey
    }

    /**
     * Set the API key prefix for the first API key authentication.
     *
     * @param apiKeyPrefix API key prefix
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    fun setApiKeyPrefix(apiKeyPrefix: String, paramName: String? = null) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.ApiKeyAuth && (paramName == null || paramName == it.paramName) } as br.com.devsrsouza.kotlin.docker.auth.ApiKeyAuth?
                ?: throw Exception("No API key authentication configured")
        auth.apiKeyPrefix = apiKeyPrefix
    }

    /**
     * Set the access token for the first OAuth2 authentication.
     *
     * @param accessToken Access token
     */
    fun setAccessToken(accessToken: String) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.OAuth } as br.com.devsrsouza.kotlin.docker.auth.OAuth?
                ?: throw Exception("No OAuth2 authentication configured")
        auth.accessToken = accessToken
    }

    /**
     * Set the access token for the first Bearer authentication.
     *
     * @param bearerToken The bearer token.
     */
    fun setBearerToken(bearerToken: String) {
        val auth = authentications?.values?.firstOrNull { it is br.com.devsrsouza.kotlin.docker.auth.HttpBearerAuth } as br.com.devsrsouza.kotlin.docker.auth.HttpBearerAuth?
                ?: throw Exception("No Bearer authentication configured")
        auth.bearerToken = bearerToken
    }

    protected suspend fun multipartFormRequest(requestConfig: RequestConfig, body: kotlin.collections.List<PartData>?, authNames: kotlin.collections.List<String>): HttpResponse {
        return request(requestConfig, MultiPartFormDataContent(body ?: listOf()), authNames)
    }

    protected suspend fun urlEncodedFormRequest(requestConfig: RequestConfig, body: Parameters?, authNames: kotlin.collections.List<String>): HttpResponse {
        return request(requestConfig, FormDataContent(body ?: Parameters.Empty), authNames)
    }

    protected suspend fun jsonRequest(requestConfig: RequestConfig, body: Any? = null, authNames: kotlin.collections.List<String>): HttpResponse {
        val contentType = (requestConfig.headers[HttpHeaders.ContentType]?.let { ContentType.parse(it) }
                ?: ContentType.Application.Json)
        return if (body != null) request(requestConfig, serializer.write(body, contentType), authNames)
        else request(requestConfig, authNames = authNames)
    }

    protected suspend fun request(requestConfig: RequestConfig, body: OutgoingContent = EmptyContent, authNames: kotlin.collections.List<String>): HttpResponse {
        return requestGeneric(requestConfig, body, authNames)
    }

    protected inline suspend fun <reified T> requestGeneric(requestConfig: RequestConfig, body: OutgoingContent = EmptyContent, authNames: kotlin.collections.List<String>): T {
        requestConfig.updateForAuth(authNames)
        val headers = requestConfig.headers

        return client.request<T> {
            this.url {
                url(host = UnixDomain.encodeHostname(baseUrl), path = "${API_PATH}${requestConfig.path}")
                requestConfig.query.forEach { query ->
                    query.value.forEach { value ->
                        parameter(query.key, value)
                    }
                }
            }
            this.method = requestConfig.method.httpMethod
            headers.filter { header -> !UNSAFE_HEADERS.contains(header.key) }.forEach { header -> this.header(header.key, header.value) }
            if (requestConfig.method in listOf(RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH))
                this.body = body

        }
    }

    inline fun <reified T : Any> requestStreaming(
        requestConfig: RequestConfig,
        body: Any? = null
    ): Flow<T> {
        val contentType = (requestConfig.headers[HttpHeaders.ContentType]?.let { ContentType.parse(it) }
            ?: ContentType.Application.Json)
        return requestStreaming(
            json,
            getUrlFromConfig(requestConfig),
            requestConfig,
            contentType,
            body,
            T::class
        )
    }

    fun getUrlFromConfig(
        requestConfig: RequestConfig
    ): Url =
        URLBuilder(
            host = UnixDomain.encodeHostname(baseUrl),
            encodedPath = "${API_PATH}${requestConfig.path}"
        ).apply {
            requestConfig.query.forEach { query ->
                query.value.forEach { value ->
                    value?.let { parameters.append(query.key, it.toString()) } ?: Unit
                }
            }
        }.build()


    fun RequestConfig.updateForAuth(authNames: kotlin.collections.List<String>) {
        for (authName in authNames) {
            val auth = authentications?.get(authName) ?: throw Exception("Authentication undefined: $authName")
            auth.apply(query, headers)
        }
    }

    private fun URLBuilder.appendPath(components: kotlin.collections.List<String>): URLBuilder = apply {
        encodedPath = encodedPath.trimEnd('/') + components.joinToString("/", prefix = "/") { it.encodeURLQueryComponent() }
    }

    val RequestMethod.httpMethod: HttpMethod
        get() = when (this) {
            RequestMethod.DELETE -> HttpMethod.Delete
            RequestMethod.GET -> HttpMethod.Get
            RequestMethod.HEAD -> HttpMethod.Head
            RequestMethod.PATCH -> HttpMethod.Patch
            RequestMethod.PUT -> HttpMethod.Put
            RequestMethod.POST -> HttpMethod.Post
            RequestMethod.OPTIONS -> HttpMethod.Options
        }
}

// https://github.com/ktorio/ktor/issues/851
private fun JsonSerializer.ignoreOutgoingContent() = IgnoreOutgoingContentJsonSerializer(this)

private class IgnoreOutgoingContentJsonSerializer(private val delegate: JsonSerializer) : JsonSerializer by delegate {
    override fun write(data: Any): OutgoingContent {
        if (data is OutgoingContent) return data
        return delegate.write(data)
    }
}
